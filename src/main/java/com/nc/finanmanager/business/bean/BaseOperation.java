package com.nc.finanmanager.business.bean;

import com.nc.finanmanager.persistance.entity.Account;
import com.nc.finanmanager.persistance.entity.Category;
import com.nc.finanmanager.persistance.entity.Transaction;
import com.nc.finanmanager.persistance.mapper.CategoryMapper;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseOperation {

    public double saldo(Account account, Date from, Date to) {
        double result = 0.0;
        double sum = 0.0;
        double diff = 0.0;

        List<Transaction> incomeList = account.getIncomeTransaction();
        List<Transaction> outcomeList = account.getOutcomeTransaction();

        for (Transaction transaction : incomeList) {
            sum += transaction.getCash();
        }
        for (Transaction transaction : outcomeList) {
            diff += transaction.getCash();
        }
        result = sum - diff;
        return result;
    }
}
