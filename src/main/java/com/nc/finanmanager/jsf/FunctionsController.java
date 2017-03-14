package com.nc.finanmanager.jsf;

import com.nc.finanmanager.business.bean.BaseOperation;
import com.nc.finanmanager.persistance.entity.Account;
import com.nc.finanmanager.persistance.mapper.AccountMapper;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ManagedBean(name = "functionsController", eager = true)
@RequestScoped
@Component
public class FunctionsController implements Serializable {

    @Autowired
    public FunctionsController(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
        accounts = accountMapper.selectAllAccounts();
        
        fromDate = new Date();
        toDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        cal.add(Calendar.DATE, 1);
        toDate = cal.getTime();
        selectedAccount = new Account();
    }

    private Double output;
    private List<Account> accounts;

    public Double getOutput() {
        return output;
    }

    public void setOutput(Double output) {
        this.output = output;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public AccountMapper getAccountMapper() {
        return accountMapper;
    }

    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    private AccountMapper accountMapper;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }
    private Date fromDate;
    private Date toDate;
    private Account selectedAccount;

    BaseOperation baseOperation;

    public BaseOperation getBaseOperation() {
        return baseOperation;
    }

    @Autowired
    public void setBaseOperation(BaseOperation baseOperation) {
        this.baseOperation = baseOperation;
    }

    public void countSaldo() {
        this.selectedAccount = accountMapper.selectAccount(selectedAccount.getId());
        this.output = baseOperation.saldo(selectedAccount, fromDate, toDate);
    }

}
