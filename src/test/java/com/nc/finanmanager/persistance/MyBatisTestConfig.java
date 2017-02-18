package com.nc.finanmanager.persistance;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@MapperScan("com.nc.finanmanager.persistance.mapper")
public class MyBatisTestConfig {
    
    @Bean
    public DataSource getDataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("com/nc/finanmanager/persistance/database-schema.sql")
                .addScript("com/nc/finanmanager/persistance/database-test-data.sql")
                .build();
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(getDataSource());
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        return sessionFactory.getObject();
    }
}
