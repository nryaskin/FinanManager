package com.nc.finanmanager.jsf;

import com.nc.finanmanager.persistance.MyBatisConfig;
import com.nc.finanmanager.persistance.entity.Account;
import com.nc.finanmanager.persistance.entity.User;
import com.nc.finanmanager.persistance.mapper.AccountMapper;
import com.nc.finanmanager.persistance.mapper.UserMapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@ManagedBean(name="transactionController", eager = true)
@RequestScoped
@Component
public class AccountController implements Serializable {
    
    private List<Account> accountsList;

    private UserMapper userMapper;
    private AccountMapper accountMapper;
    private User selectedUser;

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
    
    public AccountController(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MyBatisConfig.class);
        ctx.refresh();
        userMapper = ctx.getBean(UserMapper.class);
        this.users = userMapper.selectAllUsers();
        accountMapper = ctx.getBean(AccountMapper.class);
        accountsList = accountMapper.selectAllAccounts();
        selectedUser = new User();
    }
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    private List<User> users;

    public List<Account> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(List<Account> accountsList) {
        this.accountsList = accountsList;
    }
    
    

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    
    private String accountId;
    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    public void register(){
        Account account = new Account();
        account.setId(accountId);
        account.setBalance(balance);
        account.setUser(selectedUser);
        accountMapper.insertAccount(account);
        accountsList = accountMapper.selectAllAccounts();
    }
    
    public void delete(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        Account account = new Account();
        account.setId(id);
        accountMapper.deleteAccount(account);
        accountsList = accountMapper.selectAllAccounts();
    }
    
}
