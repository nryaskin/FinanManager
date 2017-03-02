package com.nc.finanmanager.business.bean;

import com.nc.finanmanager.persistance.entity.Account;
import com.nc.finanmanager.persistance.entity.Transaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionManager {

    class NextPrevAccount {

        public Account origin;
        public Account copy;

        private void initCopy() {
            copy = new Account();
            copy.setBalance(origin.getBalance());
            copy.setCurrency(origin.getCurrency());
            copy.setId(origin.getId());
            copy.setUser(origin.getUser());
        }
    }

    public Transaction getiHateExceptions() {
        return iHateExceptions;
    }

    public void setiHateExceptions(Transaction iHateExceptions) {
        this.iHateExceptions = iHateExceptions;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public TransactionManager(){
        iHateExceptions = null;
        state = "not started";
        accountMap = new HashMap<String, NextPrevAccount>();
        transactions = new ArrayList<Transaction>();
    }

    private Transaction iHateExceptions;
    private String state;
    Map<String, NextPrevAccount> accountMap;

    List<Transaction> transactions;

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        NextPrevAccount nextPrevSource = new NextPrevAccount();
        nextPrevSource.origin = transaction.getSource();
        nextPrevSource.initCopy();
        this.accountMap.put(transaction.getSource().getId(), nextPrevSource);
        NextPrevAccount nextPrevTarget = new NextPrevAccount();
        nextPrevTarget.origin = transaction.getTarget();
        nextPrevTarget.initCopy();
        this.accountMap.put(transaction.getTarget().getId(), nextPrevTarget);
        transaction.setState("hold");
    }

    public void begin() {
        state = "success";
        for (Transaction transaction : transactions) {
            transfer(transaction);
            if (iHateExceptions != null) {
                state = "error";
                break;
            }
        }
    }

    private void transfer(Transaction transaction) {
        Account source = accountMap.get(transaction.getSource().getId()).copy;
        Account target = accountMap.get(transaction.getTarget().getId()).copy;
        Double money = transaction.getCash();
        if (source.getBalance() < transaction.getCash()) {
            transaction.setState("transaction error");
            iHateExceptions = transaction;
        } else {
            source.setBalance(source.getBalance() - money);
            target.setBalance(target.getBalance() + money);
            transaction.setState("success");
        }
    }
    
    public void commit(){
        for (Transaction transaction : transactions) {
            transaction.setSource(accountMap.get(transaction.getSource().getId()).copy);
            transaction.setTarget(accountMap.get(transaction.getTarget().getId()).copy);
        }
    }
    
    public void rollback(){
        for (Transaction transaction : transactions) {
            transaction.setSource(accountMap.get(transaction.getSource().getId()).copy);
            transaction.setTarget(accountMap.get(transaction.getTarget().getId()).copy);
        }
    }    
}
