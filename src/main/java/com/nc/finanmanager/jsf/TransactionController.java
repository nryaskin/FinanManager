package com.nc.finanmanager.jsf;

import com.nc.finanmanager.persistance.entity.Transaction;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.stereotype.Component;

@ManagedBean(name="transactionController", eager = true)
@RequestScoped
@Component
public class TransactionController {
    
    List<Transaction> transactionsList;
    
    
}
