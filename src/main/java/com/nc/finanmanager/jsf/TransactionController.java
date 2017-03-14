package com.nc.finanmanager.jsf;

import com.nc.finanmanager.business.bean.Reconciler;
import com.nc.finanmanager.business.bean.TransactionManager;
import com.nc.finanmanager.persistance.entity.Account;
import com.nc.finanmanager.persistance.entity.Transaction;
import com.nc.finanmanager.persistance.mapper.AccountMapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ManagedBean(name = "transactionController", eager = true)
@SessionScoped
@Component
public class TransactionController implements Serializable {

    private TransactionManager transactionManager;
    private Transaction selectedTransaction;
    private List<Transaction> transactions;
    private List<Account> accounts;
    private AccountMapper accountMapper;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    private void initTransaction() {
        selectedTransaction = new Transaction();
    }

    @Autowired
    public TransactionController(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
        initTransaction();
        transactions = new ArrayList<Transaction>();
        accounts = accountMapper.selectAllAccounts();
        statr = "no";
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

    private Reconciler reconciler;

    public Reconciler getReconciler() {
        return reconciler;
    }

    @Autowired
    public void setReconciler(Reconciler reconciler) {
        this.reconciler = reconciler;
    }

    public void addVirtualTransaction() {
        transactions.add(selectedTransaction);
        reconciler.reconcilTransactionUnits(selectedTransaction);
        initTransaction();
    }

    public void deleteVirtualTransaction() {
        Transaction transactionToRemove = null;
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        for (Transaction transaction : transactions) {
            if (transaction.getId().toString().equals(id)) {
                transactionToRemove = transaction;
                break;
            }
        }
        if (transactionToRemove != null) {
            transactions.remove(transactionToRemove);
        }
    }

    public void performTransaction() {
        statr = "youootuot";
        transactionManager.addTransactions(transactions);
        transactionManager.execute();
        statr += "hi" + transactionManager.getState();
    }

    private String statr;

    public String getStatr() {
        return statr;
    }

    public void setStatr(String statr) {
        this.statr = statr;
    }
}
