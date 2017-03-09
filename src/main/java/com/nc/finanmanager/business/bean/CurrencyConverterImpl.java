package com.nc.finanmanager.business.bean;

import com.nc.finanmanager.persistance.entity.Currency;

public class CurrencyConverterImpl implements CurrencyConverter {

    @Override
    public Double convert(Currency source, Currency target, Double current) {
        Double res = 0.0;
        if (source.getCurrencyId() == target.getCurrencyId()) {
            return current;
        }
        switch (source.getType()) {
            case "DOLLAR":
                switch (target.getType()) {
                    case "EURO":
                        res = 0.95 * current;
                        break;
                    case "RUBLE":
                        res = 58.39 * current;
                        break;
                }
                break;
            case "EURO":
                switch (source.getType()) {
                    case "DOLLAR":
                        res = current / 0.95;
                        break;
                    case "RUBLE":
                        res = current * 61.64;
                        break;
                }
                break;
            case "RUBLE":
                switch (source.getType()) {
                    case "DOLLAR":
                        res = current / 58.39;
                        break;
                    case "EURO":
                        res = current / 61.64;
                        break;
                }
                break;
        }

        return res;
    }

}
