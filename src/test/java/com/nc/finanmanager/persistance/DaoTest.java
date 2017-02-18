package com.nc.finanmanager.persistance;

import com.nc.finanmanager.persistance.entity.Account;
import com.nc.finanmanager.persistance.entity.Category;
import com.nc.finanmanager.persistance.entity.Currency;
import com.nc.finanmanager.persistance.entity.User;
import com.nc.finanmanager.persistance.mapper.AccountMapper;
import com.nc.finanmanager.persistance.mapper.CategoryMapper;
import com.nc.finanmanager.persistance.mapper.CurrencyMapper;
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
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext();
        ctx.register(MyBatisConfig.class);
        ctx.refresh();
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void updateFirstCategoryTest(){
        /*CategoryMapper categoryMapper = ctx.getBean(CategoryMapper.class);
        Category category = categoryMapper.selectCategory(1);
        category.setCategoryName("food");
        categoryMapper.updateCategory(category);
        Assert.assertEquals("Not today","food", categoryMapper.selectCategory(1).getCategoryName());
        */
    }
    
    @Test
    public void selectCurrencyTest(){
        CurrencyMapper currencyMapper = ctx.getBean(CurrencyMapper.class);
        Assert.assertEquals("No no no", "EURO", currencyMapper.selectAllCurrency().get(0).getType());
    }
    
    @Test
    public void selectUserTest(){
        UserMapper userMapper = ctx.getBean(UserMapper.class);        
        Assert.assertEquals("Not today","Nikitos", userMapper.selectAllUsers().get(0).getUsername());
    }

    @Test
    public void deleteCurrencyTest(){
        CurrencyMapper currencyMapper = ctx.getBean(CurrencyMapper.class);
        Assert.assertEquals("No no no", "EURO", currencyMapper.selectAllCurrency().get(0).getType());
    }
    
    @Test
    public void insertAccountTest(){
        AccountMapper accountMapper = ctx.getBean(AccountMapper.class);
        UserMapper userMapper = ctx.getBean(UserMapper.class);
        CurrencyMapper currencyMapper = ctx.getBean(CurrencyMapper.class);
        
        User user = userMapper.selectAllUsers().get(0);
        Currency currency = currencyMapper.selectAllCurrency().get(0);
        Account account = new Account();
        account.setBalance(10000);
        account.setCurrency(currency);
        account.setUser(user);
        account.setId("0000-0000-0000-0000");
        accountMapper.insertAccount(account);
        assertEquals("No no no", "0000-0000-0000-0000", accountMapper.selectAllAccounts().get(0).getId());
        assertEquals("No no no", user.getUsername(), accountMapper.selectAllAccounts().get(0).getUser().getUsername());
        accountMapper.deleteAccount(account);    
    }
    
    @Test
    public void insertCategoryTest(){
        CategoryMapper categoryMapper = ctx.getBean(CategoryMapper.class);
        Category category = new Category();
        category.setCategoryId("Chocolate");
        categoryMapper.insertCategory(category);
        assertEquals("YO, man", category.getCategoryId(), categoryMapper.selectAllCategories().get(0).getCategoryId());
        assertEquals("YO, man", category.getCategoryId(), categoryMapper.selectCategory(category.getCategoryId()).getCategoryId());
        categoryMapper.deleteCategory(categoryMapper.selectAllCategories().get(0));
    }
}
