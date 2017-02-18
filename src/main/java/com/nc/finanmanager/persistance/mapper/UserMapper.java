package com.nc.finanmanager.persistance.mapper;

import com.nc.finanmanager.persistance.entity.Account;
import com.nc.finanmanager.persistance.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Results({
    @Result(property = "username", column = "username"),
    @Result(property = "password" , column = "password")
    })
    @Insert("INSERT into users(username, password) VALUES(#{username}, #{password})")
    void insertUser(User user);
    
    @Results({
          @Result(property = "username", column = "username"),
          @Result(property = "password", column = "password"),
          @Result(property ="accounts", javaType = List.class, column = "username", many = @Many(select = "getUserAccounts"))
        })
    @Select("SELECT * from users where username = #{username}")
    User selectUserByUsername(String username);
    
    @Results({
          @Result(property = "username", column = "username"),
          @Result(property = "password", column = "password"),
          @Result(property ="accounts", javaType = List.class, column = "username", many = @Many(select = "getUserAccounts"))
        })
    @Select("SELECT * from users")
    List<User> selectAllUsers();
    
    
    @Select("SELECT * from account WHERE username=#{username}")
    List<Account> getUserAccounts(String username);
    
    
    @Update("UPDATE users SET password=#{password} WHERE username=#{username}")
    void updateUser(User user);
    
    
    @Results({
    @Result(property = "username", column = "username"),
    @Result(property = "password" , column = "password")
    })
    @Delete("DELETE FROM users WHERE username=#{username}")
    void deleteUser(User user);
}
