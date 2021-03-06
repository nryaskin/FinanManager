package com.nc.finanmanager.persistance.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Transaction  implements Serializable {
    private Integer id;
    private Account source;
    private Account target;
    private Category category;
    private String state;
    private Double cash;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Transaction(){       
        this.setId(UUID.randomUUID().hashCode());
        this.setSource(new Account());
        this.setTarget(new Account());
        this.setState("not executed");
        this.category = new Category();
    }
    
    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    private Currency currency;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Account getSource() {
        return source;
    }

    public void setSource(Account source) {
        this.source = source;
    }

    public Account getTarget() {
        return target;
    }

    public void setTarget(Account target) {
        this.target = target;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
