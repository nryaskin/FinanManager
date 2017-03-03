package com.nc.finanmanager.persistance.entity;

import java.io.Serializable;
import java.util.List;

public class Account{
    
    private String id;
    private User user;
    private double balance;
    private Currency currency;

    private List<Transaction> incomeTransaction;
    private List<Transaction> outcomeTransaction;

    public List<Transaction> getIncomeTransaction() {
        return incomeTransaction;
    }

    public void setIncomeTransaction(List<Transaction> incomeTransaction) {
        this.incomeTransaction = incomeTransaction;
    }

    public List<Transaction> getOutcomeTransaction() {
        return outcomeTransaction;
    }

    public void setOutcomeTransaction(List<Transaction> outcomeTransaction) {
        this.outcomeTransaction = outcomeTransaction;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
