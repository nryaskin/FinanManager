package com.nc.finanmanager.persistance.entity;

import java.io.Serializable;
import java.util.List;

public class Category  implements Serializable {
    private String categoryId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
}
