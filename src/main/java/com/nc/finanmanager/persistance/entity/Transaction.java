package com.nc.finanmanager.persistance.entity;

public class Transaction {
    private Integer id;
    private String username;
    private Account source;
    private Account target;
    private Category category;
    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
