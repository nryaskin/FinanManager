
package com.nc.finanmanager.business;

import com.nc.finanmanager.business.bean.BaseOperation;
import com.nc.finanmanager.business.bean.Reconciler;
import com.nc.finanmanager.business.bean.TransactionManager;
import com.nc.finanmanager.jsf.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({"com.nc.finanmanager.business.bean", "com.nc.finanmanager.jsf"})
public class AppConfig {

    @Bean 
    public TransactionManager getTransactionManager(){
        return new TransactionManager();
    }
    
    @Bean
    public Reconciler getReconciler(){
        return new Reconciler();
    }
    
}
