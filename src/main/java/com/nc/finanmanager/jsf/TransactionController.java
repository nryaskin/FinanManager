package com.nc.finanmanager.jsf;

import com.nc.finanmanager.business.bean.TransactionManager;
import com.nc.finanmanager.persistance.entity.Account;
import com.nc.finanmanager.persistance.entity.Transaction;
import com.nc.finanmanager.persistance.mapper.AccountMapper;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ManagedBean(name = "transactionController", eager = true)
@RequestScoped
@Component
public class TransactionController {

    private TransactionManager transactionManager;
    private Transaction selectedTransaction;
    private List<Transaction> transactions;
    private List<Account> accounts;
    private AccountMapper accountMapper;

    private void initTransaction() {
        selectedTransaction = new Transaction();
        selectedTransaction.setSource(new Account());
        selectedTransaction.setTarget(new Account());
        selectedTransaction.setState("not executed");
    }

    @Autowired
    public TransactionController(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
        initTransaction();
        transactions = new ArrayList<Transaction>();
        accounts = accountMapper.selectAllAccounts();

    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Transaction getSelectedTransaction() {
        return selectedTransaction;
    }

    public void setSelectedTransaction(Transaction selectedTransaction) {
        this.selectedTransaction = selectedTransaction;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    @Autowired
    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void addVirtualTransaction() {
        transactions.add(selectedTransaction);
        initTransaction();

    }

    public void deleteVirtualTransaction() {
        Transaction transactionToRemove = null;
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        for (Transaction transaction : transactions) {
            if (transaction.getId().toString() == id) {
                transactionToRemove = transaction;
                break;
            }
        }
        if(transactionToRemove != null)
            transactions.remove(transactionToRemove);

    }

    public void performTransaction() {
        transactionManager.addTransactions(transactions);
        transactionManager.begin();
        
    }

}
