package com.nc.finanmanager.persistance;

import com.nc.finanmanager.persistance.entity.Category;
import com.nc.finanmanager.persistance.mapper.CategoryMapper;
import com.nc.finanmanager.persistance.mapper.ItemMapper;
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
        CategoryMapper categoryMapper = ctx.getBean(CategoryMapper.class);
        Category category = categoryMapper.selectCategory(1);
        category.setCategoryName("food");
        categoryMapper.updateCategory(category);
        Assert.assertEquals("Not today","food", categoryMapper.selectCategory(1).getCategoryName());
        
    }
    
    @Test
    public void countItemsTest(){
        ItemMapper itemMapper = ctx.getBean(ItemMapper.class);
        Assert.assertEquals("Not today",12, itemMapper.selectItems().size());
        
    }
    
    @Test
    public void countItemsInCategoriesTest(){
        CategoryMapper categoryMapper = ctx.getBean(CategoryMapper.class);
        Category category  = categoryMapper.selectCategory(1);
        Assert.assertEquals("Not today",12, category.getItems().size());
        
    }
    
}
