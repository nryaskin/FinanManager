package com.nc.finanmanager.business.bean;

import com.nc.finanmanager.persistance.entity.Transaction;
import com.nc.finanmanager.persistance.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Reconciler {

    private AccountMapper accountMapper;

    public AccountMapper getAccountMapper() {
        return accountMapper;
    }
    
    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }
    
    public Reconciler(){
        
    }
    
    
    public void reconcilTransactionUnits(Transaction transaction){
     transaction.setSource(accountMapper.selectAccount(transaction.getSource().getId()));
     transaction.setTarget(accountMapper.selectAccount(transaction.getTarget().getId()));
    }
}
