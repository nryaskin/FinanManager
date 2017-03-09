package com.nc.finanmanager.business.bean;

import com.nc.finanmanager.persistance.entity.Account;
import com.nc.finanmanager.persistance.entity.Transaction;
import com.nc.finanmanager.persistance.mapper.AccountMapper;
import com.nc.finanmanager.persistance.mapper.TransactionMapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionManager implements Serializable {

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

    private TransactionMapper transactionMapper;
    private AccountMapper accountMapper;

    public AccountMapper getAccountMapper() {
        return accountMapper;
    }
    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public TransactionMapper getTransactionMapper() {
        return transactionMapper;
    }
    @Autowired
    public void setTransactionMapper(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    
    private CurrencyConverter currencyConverter;

    public CurrencyConverter getCurrencyConverter() {
        return currencyConverter;
    }

    @Autowired
    public void setCurrencyConverter(CurrencyConverter currencyConverter) {
        this.currencyConverter = currencyConverter;
    }
    
    private Reconciler reconciler;

    public Reconciler getReconciler() {
        return reconciler;
    }

    @Autowired
    public void setReconciler(Reconciler reconciler) {
        this.reconciler = reconciler;
    }
    
    public Transaction getiExceptions() {
        return iExceptions;
    }

    public void setiExceptions(Transaction iExceptions) {
        this.iExceptions = iExceptions;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public TransactionManager(){
        iExceptions = null;
        state = "not started";
        accountMap = new HashMap<String, NextPrevAccount>();
        transactions = new ArrayList<Transaction>();
    }

    private Transaction iExceptions;
    private String state;
    Map<String, NextPrevAccount> accountMap;

    List<Transaction> transactions;

    public void addTransaction(Transaction transaction) {
        reconciler.reconcilTransactionUnits(transaction);
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
    
    public void addTransactions(List<Transaction> transactions){
        for(Transaction transaction: transactions){
            addTransaction(transaction);
        }
    }

    public void begin() {
        state = "success";
        for (Transaction transaction : transactions) {
            transfer(transaction);
            if (iExceptions != null) {
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
            iExceptions = transaction;
        } else {
            source.setBalance(source.getBalance() - money);
            target.setBalance(target.getBalance() + currencyConverter.convert(source.getCurrency(), target.getCurrency(), money));
            transaction.setState("success");
        }
    }
    
    public void commit(){
        for(Map.Entry<String, NextPrevAccount> entry : accountMap.entrySet()){
             accountMapper.updateAccount(entry.getValue().copy);
        }
        for (Transaction transaction : transactions) {
            transaction.setSource(accountMap.get(transaction.getSource().getId()).copy);
            transaction.setTarget(accountMap.get(transaction.getTarget().getId()).copy);
            transactionMapper.insertTransaction(transaction);
        }
    }
    
    public void rollback(){
        for (Transaction transaction : transactions) {
            transaction.setSource(accountMap.get(transaction.getSource().getId()).origin);
            transaction.setTarget(accountMap.get(transaction.getTarget().getId()).origin);
        }
    }    
}
