package com.nc.finanmanager.persistance;

import com.nc.finanmanager.persistance.entity.Category;
import com.nc.finanmanager.persistance.mapper.CategoryMapper;
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
        ctx.register(MyBatisTestConfig.class);
        ctx.refresh();
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void updateFirstCategoryTest(){
        CategoryMapper categoryMapper = ctx.getBean(CategoryMapper.class);
        Category category = categoryMapper.selectAllCategories().get(0);
        category.setCategoryName("Chocolate");
        categoryMapper.updateCategory(category);
        Assert.assertEquals("Not today","Chocolate", categoryMapper.selectAllCategories().get(0));
        
    }
    
    /*@Test
    public void insertCategoryTest(){
        CategoryMapper categoryMapper = ctx.getBean(CategoryMapper.class);
        Category category  = new Category();
        category.setCategoryName("Cars");
        categoryMapper.insertCategory(category);
    } */
    
}
