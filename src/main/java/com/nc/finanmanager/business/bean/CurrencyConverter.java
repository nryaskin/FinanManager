package com.nc.finanmanager.business.bean;

import com.nc.finanmanager.persistance.entity.Currency;

public interface CurrencyConverter {
    Double convert(Currency source, Currency target, Double current);
}
