package com.nc.finanmanager.persistance.entity;

import java.io.Serializable;

public class Currency implements Serializable {
    private Integer currencyId;
    private String type;

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
