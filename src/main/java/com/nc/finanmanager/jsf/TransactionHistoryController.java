package com.nc.finanmanager.jsf;

import com.nc.finanmanager.persistance.entity.Transaction;
import com.nc.finanmanager.persistance.mapper.TransactionMapper;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ManagedBean(name = "transactionHistoryController", eager = true)
@SessionScoped
@Component
public class TransactionHistoryController implements Serializable {
    
    private List<Transaction> transactionsHistoryList;

    private TransactionMapper transactionMapper;
    
    public List<Transaction> getTransactionsHistoryList() {
        return transactionsHistoryList;
    }

    public void setTransactionsHistoryList(List<Transaction> transactionsHistoryList) {
        this.transactionsHistoryList = transactionsHistoryList;
    }
    
    @Autowired
    public TransactionHistoryController(TransactionMapper transactionMapper){
        this.transactionMapper = transactionMapper;
        transactionsHistoryList = transactionMapper.selectAllTransactions();
    }
}
