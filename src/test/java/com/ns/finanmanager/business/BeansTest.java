package com.ns.finanmanager.business;

import com.nc.finanmanager.business.AppConfig;
import com.nc.finanmanager.business.bean.BaseOperation;
import com.nc.finanmanager.business.bean.TransactionManager;
import com.nc.finanmanager.persistance.MyBatisConfig;
import com.nc.finanmanager.persistance.entity.Account;
import com.nc.finanmanager.persistance.entity.Category;
import com.nc.finanmanager.persistance.entity.Transaction;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeansTest {

    Transaction badTransaction;
    Transaction okTransaction;
    
    
    @Before
    public void init(){
        badTransaction = new Transaction();
        okTransaction = new Transaction();
        
        Account sourceAccount = new Account();
        Account targetAccount = new Account();

        sourceAccount.setBalance(2000);
        targetAccount.setBalance(10000);

        badTransaction.setSource(sourceAccount);
        badTransaction.setTarget(targetAccount);
        badTransaction.setCash(3000.0);
        
        Account sourceAccountok = new Account();
        Account targetAccountok = new Account();

        sourceAccountok.setBalance(10000);
        targetAccountok.setBalance(2000);

        okTransaction.setSource(sourceAccountok);
        okTransaction.setTarget(targetAccountok);
        okTransaction.setCash(3000.0);


    }

    
    @Test
    public void transactionManagerTest(){
        Transaction transaction = new Transaction();
        Account accountSource = new Account();
        Account accountTarget = new Account();
        accountSource.setId("0000-0000-0000-0000");
        accountSource.setBalance(100000);
        accountTarget.setId("0000-0000-0000-0001");
        accountTarget.setBalance(2000);
        transaction.setSource(accountSource);
        transaction.setTarget(accountTarget);
        transaction.setCash(20000.0);
        
        TransactionManager transactionManager = new TransactionManager();
        
        transactionManager.addTransaction(transaction);
        transactionManager.begin();
        Assert.assertEquals("fuck", "success", transactionManager.getState());
        transactionManager.commit();
        Assert.assertEquals("transaction shouldn't fail but it failed", "success", transaction.getState());
        Assert.assertEquals("purumpum", "0000-0000-0000-0000", transaction.getSource().getId());
        Assert.assertEquals("nnot correct target id", "0000-0000-0000-0001", transaction.getTarget().getId());
        Assert.assertEquals("no changes in source", 80000.0, transaction.getSource().getBalance());
        Assert.assertEquals("no changes in target", 22000.0, transaction.getTarget().getBalance());
        
    }
    
}
