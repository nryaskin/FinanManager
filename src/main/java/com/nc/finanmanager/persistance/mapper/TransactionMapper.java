package com.nc.finanmanager.persistance.mapper;

import com.nc.finanmanager.persistance.entity.Account;
import com.nc.finanmanager.persistance.entity.Category;
import com.nc.finanmanager.persistance.entity.Currency;
import com.nc.finanmanager.persistance.entity.Transaction;
import com.nc.finanmanager.persistance.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface TransactionMapper {
    
    @Results({
        @Result(property = "id", column="id"),
        @Result(property="state", column = "state"),
        @Result(property = "source", javaType = Account.class, column = "source" , one=@One(select = "selectAccount")),
        @Result(property = "target", javaType = Account.class, column = "target" , one=@One(select = "selectAccount")),
        @Result(property = "category", javaType = Category.class, column = "category_id", one=@One(select = "selectCategory")),
        @Result(property = "currency", javaType = Currency.class, column = "current_id", one=@One(select = "getTransactionCurrency"))
    })
    @Select("SELECT * FROM transaction")
    List<Transaction> selectAllTransactions();

    @Results({
        @Result(property = "id", column="id"),
        @Result(property="state", column = "state"),
        @Result(property = "source", javaType = Account.class, column = "source" , one=@One(select = "selectAccount")),
        @Result(property = "target", javaType = Account.class, column = "target" , one=@One(select = "selectAccount")),
        @Result(property = "category", javaType = Category.class, column = "category_id", one=@One(select = "selectCategory")),
        @Result(property = "currency", javaType = Currency.class, column = "current_id", one=@One(select = "getTransactionCurrency"))
    })
    @Select("SELECT * FROM transaction WHERE id = #{id}")
    void selectTransactionById(Integer id);
    
    @Select("SELECT * FROM currency WHERE current_id = #{currencyId}")
    Currency getTransactionCurrency(Integer currencyId);
    
    @Select("SELECT * FROM account WHERE id=#{accountId}")
    Account selectAccount(String accountId);
    
    @Results({
        @Result(property = "categoryId", column = "id_category")
    })
    @Select("SELECT * FROM category WHERE id_category = #{categoryId}")
    Category selectCategory(String categoryId);
    
    @Insert("INSERT INTO transaction(source, target, category_id, state, current_id) VALUES(#{source.id}, #{target.id}, #{category.categoryId}, #{state}, #{currency.currencyId})")
    void insertTransaction(Transaction transaction);
    
    
    @Delete("DELETE FROM transaction WHERE id = #{id}")
    void deleteTransaction(Transaction transaction);
    
    
}
