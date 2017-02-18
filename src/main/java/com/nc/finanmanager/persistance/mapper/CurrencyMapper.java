package com.nc.finanmanager.persistance.mapper;

import com.nc.finanmanager.persistance.entity.Currency;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CurrencyMapper {
    
    @Insert("INSERT INTO currency(type) VALUES(#{type})")
    void insertCurrency(Currency currency);
    
    @Select("SELECT * FROM currency WHERE current_id = #{currencyId}")
    Currency selectCurrencyById(Integer currrencyId);
    
    
    
    @Results({
        @Result(property = "currencyId", column = "current_id"),
        @Result(property = "type", column = "type")
    })
    @Select("SELECT * FROM currency")
    List<Currency> selectAllCurrency();
    
    @Update("UPDATE currency SET type = #{type}")
    void updateCurrency(Currency currency);
    
    @Results({
        @Result(property = "currencyId", column = "current_id")
    })
    @Delete("DELETE FROM currency WHERE current_id=#{currencyId}")
    void deleteCurrency(Currency currency);
    
}
