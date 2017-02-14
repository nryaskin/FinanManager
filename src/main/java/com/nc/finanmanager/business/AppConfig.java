
package com.nc.finanmanager.business;

import com.nc.finanmanager.business.bean.BaseOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.nc.com.nc.finanmanager.business.bean")
public class AppConfig {
    @Bean
    public BaseOperation getBaseOperation(){
        return new BaseOperation();
    }
    
}
