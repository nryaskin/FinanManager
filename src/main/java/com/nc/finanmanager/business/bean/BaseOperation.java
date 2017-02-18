package com.nc.finanmanager.business.bean;

import com.nc.finanmanager.persistance.entity.Category;
import com.nc.finanmanager.persistance.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseOperation {
        
        
        public int size(Category category){
            return 0;
        }
       
        public int outcomeForCategory(Category category){
            return 2;
        }
}
