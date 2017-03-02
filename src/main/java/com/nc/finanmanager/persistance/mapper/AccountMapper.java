package com.nc.finanmanager.persistance.mapper;

import com.nc.finanmanager.persistance.entity.Account;
import com.nc.finanmanager.persistance.entity.Currency;
import com.nc.finanmanager.persistance.entity.Transaction;
import com.nc.finanmanager.persistance.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface AccountMapper {
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "balance", column = "balance"),
        @Result(property = "user", javaType = User.class, column = "username", one=@One(select = "getAccountsUser")),
        @Result(property = "currency", javaType = Currency.class, column = "current_id", one=@One(select = "getAccountsCurrency")),
        @Result(property = "incomeTransaction", javaType = List.class, column = "id", many=@Many(select = "getIncomeTransactions")),
        @Result(property = "outcomeTransaction", javaType = List.class, column = "id", many=@Many(select = "getOutcomeTransactions"))
    })
    @Select("SELECT * FROM account WHERE id=#{id}")
    Account selectAccount(String id);
    
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "balance", column = "balance"),
        @Result(property = "user", javaType = User.class, column = "username", one=@One(select = "getAccountsUser")),
        @Result(property = "currency", javaType = Currency.class, column = "current_id", one=@One(select = "getAccountsCurrency")),
        @Result(property = "incomeTransaction", javaType = List.class, column = "id", many=@Many(select = "getIncomeTransactions")),
        @Result(property = "outcomeTransaction", javaType = List.class, column = "id", many=@Many(select = "getOutcomeTransactions"))
    })
    @Select("SELECT * FROM account")
    List<Account> selectAllAccounts();
    
    
    @Select("Select * FROM users WHERE username = #{username}")
    User getAccountsUser(String username);
    
    @Select("SELECT * FROM currency WHERE current_id = #{currencyId}")
    Currency getAccountsCurrency(Integer currencyId);
    
    @Select("SELECT * FROM transaction WHERE target = #{id}")
    List<Transaction> getIncomeTransactions(String id);
    
    @Select("SELECT * FROM transaction WHERE source = #{id}")
    List<Transaction> getOutcomeTransactions(String id);
    
    
    
    @Insert("INSERT INTO account(id, balance, username, current_id) VALUES(#{id}, #{balance}, #{user.username}, #{currency.currencyId})")
    void insertAccount(Account account);
    
    @Delete("DELETE FROM account WHERE id = #{id}")
    void deleteAccount(Account account);
}
