package com.nc.finanmanager.persistance;

import com.nc.finanmanager.persistance.entity.Account;
import com.nc.finanmanager.persistance.entity.Category;
import com.nc.finanmanager.persistance.entity.Currency;
import com.nc.finanmanager.persistance.entity.Transaction;
import com.nc.finanmanager.persistance.entity.User;
import com.nc.finanmanager.persistance.mapper.AccountMapper;
import com.nc.finanmanager.persistance.mapper.CategoryMapper;
import com.nc.finanmanager.persistance.mapper.CurrencyMapper;
import com.nc.finanmanager.persistance.mapper.TransactionMapper;
import com.nc.finanmanager.persistance.mapper.UserMapper;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DaoTest {

    AnnotationConfigApplicationContext ctx;

    public DaoTest() {
    }
//    
//    @Test
//    public void setCurrency(){
//        CurrencyMapper currencyMapper = ctx.getBean(CurrencyMapper.class);
//        Currency currency = new Currency();
//        currency.setType("DOLLAR");
//        currencyMapper.insertCurrency(currency);
//        Currency currency1 = new Currency();
//        currency1.setType("RUBLE");
//        currencyMapper.insertCurrency(currency1);
//    }

//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
////
//    @Before
//    public void setUp() {
//        ctx = new AnnotationConfigApplicationContext();
//        ctx.register(MyBatisConfig.class);
//        ctx.refresh();
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void InsertUserTest() {
//
//    }
//
//    @Test
//    public void selectCurrencyTest() {
//        CurrencyMapper currencyMapper = ctx.getBean(CurrencyMapper.class);
//        /*Currency currency = new Currency();
//        currency.setType("EURO");
//        currencyMapper.insertCurrency(currency);*/
//        Assert.assertEquals("No no no", "EURO", currencyMapper.selectAllCurrency().get(0).getType());
//    }
//
//    @Test
//    public void selectUserTest() {
//        UserMapper userMapper = ctx.getBean(UserMapper.class);
//        /*User user = new User();
//        user.setUsername("Serega");
//        user.setPassword("pass");
//        userMapper.insertUser(user);*/
////        Assert.assertEquals("Not today", "Nikitos", userMapper.selectAllUsers().get(0).getUsername());
//    }
//    
//    @Test
//    public void insertAccountTest() {
//        /*AccountMapper accountMapper = ctx.getBean(AccountMapper.class);
//        UserMapper userMapper = ctx.getBean(UserMapper.class);
//        CurrencyMapper currencyMapper = ctx.getBean(CurrencyMapper.class);
//
//        User user = userMapper.selectAllUsers().get(0);
//        Currency currency = currencyMapper.selectAllCurrency().get(0);
//        Account account = new Account();
//        account.setBalance(10000);
//        account.setCurrency(currency);
//        account.setUser(user);
//        account.setId("0000-0000-0000-0000");
//        accountMapper.insertAccount(account);
//        assertEquals("No no no", "0000-0000-0000-0000", accountMapper.selectAllAccounts().get(0).getId());
//        assertEquals("No no no", user.getUsername(), accountMapper.selectAllAccounts().get(0).getUser().getUsername());
//        accountMapper.deleteAccount(account);*/
//    }
//
//    @Test
//    public void insertCategoryTest() {
//        /*CategoryMapper categoryMapper = ctx.getBean(CategoryMapper.class);
//        Category category = new Category();
//        category.setCategoryId("Chocolate");
//        categoryMapper.insertCategory(category);
//        assertEquals("YO, man", category.getCategoryId(), categoryMapper.selectAllCategories().get(0).getCategoryId());
//        assertEquals("YO, man", category.getCategoryId(), categoryMapper.selectCategory(category.getCategoryId()).getCategoryId());
//        categoryMapper.deleteCategory(categoryMapper.selectAllCategories().get(0));
//*/    
//    }
//
//    @Test
//    public void insertTransactionTest() {
//        AccountMapper accountMapper = ctx.getBean(AccountMapper.class);
//        UserMapper userMapper = ctx.getBean(UserMapper.class);
//        CurrencyMapper currencyMapper = ctx.getBean(CurrencyMapper.class);
//        CategoryMapper categoryMapper = ctx.getBean(CategoryMapper.class);
//        TransactionMapper transactionMapper = ctx.getBean(TransactionMapper.class);
//
//        User user = userMapper.selectAllUsers().get(0);
//        Currency currency = currencyMapper.selectAllCurrency().get(0);
//
//        Account accountSource = new Account();
//        accountSource.setBalance(10000);
//        accountSource.setCurrency(currency);
//        accountSource.setUser(user);
//        accountSource.setId("0000-0000-0000-0000");
//        accountMapper.insertAccount(accountSource);
//        assertEquals("No no no", "0000-0000-0000-0000", accountMapper.selectAllAccounts().get(0).getId());
//        assertEquals("No no no", user.getUsername(), accountMapper.selectAllAccounts().get(0).getUser().getUsername());
//
//        Account accountTarget = new Account();
//        accountTarget.setBalance(10000);
//        accountTarget.setCurrency(currency);
//        accountTarget.setUser(user);
//        accountTarget.setId("0000-0000-0000-0001");
//        accountMapper.insertAccount(accountTarget);
//        assertEquals("No no no", "0000-0000-0000-0001", accountMapper.selectAllAccounts().get(1).getId());
//        assertEquals("No no no", user.getUsername(), accountMapper.selectAllAccounts().get(1).getUser().getUsername());
//
//        Category category = new Category();
//        category.setCategoryId("Cakes");
//        categoryMapper.insertCategory(category);
//
//        Transaction transaction = new Transaction();
//        transaction.setCategory(category);
//        transaction.setSource(accountSource);
//        transaction.setTarget(accountTarget);
//        transaction.setState("success");
//        transaction.setCurrency(currency);
//        transactionMapper.insertTransaction(transaction);
//        
//        assertEquals("Ururururr", transaction.getState(), transactionMapper.selectAllTransactions().get(0).getState());
//        assertEquals("Category", category.getCategoryId(), transactionMapper.selectAllTransactions().get(0).getCategory().getCategoryId());
//        assertEquals("source", accountSource.getId(), transactionMapper.selectAllTransactions().get(0).getSource().getId());
//        assertEquals("target", accountTarget.getId(), transactionMapper.selectAllTransactions().get(0).getTarget().getId());
//
//        transactionMapper.deleteTransaction(transactionMapper.selectAllTransactions().get(0));
//        categoryMapper.deleteCategory(categoryMapper.selectAllCategories().get(0));
//        accountMapper.deleteAccount(accountMapper.selectAllAccounts().get(1));
//        accountMapper.deleteAccount(accountMapper.selectAllAccounts().get(0));
//
//    }
}
