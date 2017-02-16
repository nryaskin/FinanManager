package com.nc.finanmanager.persistance;

import com.nc.finanmanager.business.AppConfig;
import com.nc.finanmanager.business.bean.BaseOperation;
import com.nc.finanmanager.persistance.entity.Category;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeansTest {
    
    @Test
    public void beanInjectionTest(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class, MyBatisConfig.class);
        ctx.refresh();
        
        BaseOperation baseOperation = ctx.getBean(BaseOperation.class);
        Assert.assertEquals("notToday", baseOperation.size(new Category()), 8);
        Assert.assertEquals("notToday", baseOperation.outcomeForCategory(new Category()), 2);
}
    
}
