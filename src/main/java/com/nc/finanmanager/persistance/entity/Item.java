package com.nc.finanmanager.persistance.entity;

import java.sql.Date;

public class Item {
    private Integer itemId;
    private Integer categoryId;
    private Integer expenses;
    private Date date;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getExpenses() {
        return expenses;
    }

    public void setExpenses(Integer expenses) {
        this.expenses = expenses;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
