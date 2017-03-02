package com.nc.finanmanager.jsf;

import com.nc.finanmanager.persistance.MyBatisConfig;
import com.nc.finanmanager.persistance.entity.User;
import com.nc.finanmanager.persistance.mapper.UserMapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@ManagedBean(name="userController", eager=true)
@RequestScoped
@Component
public class UserController implements Serializable{
    
    private List<User> usersList;

    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    UserMapper userMapper;
    
    public UserController(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MyBatisConfig.class);
        ctx.refresh();
        userMapper = ctx.getBean(UserMapper.class);
        this.usersList = userMapper.selectAllUsers();
    }
    
    public void register(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userMapper.insertUser(user);
        usersList = userMapper.selectAllUsers();
        username = "";
        password="";
    }
    
    public void delete(){
        String username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        User user = new User();
        user.setUsername(username);
        userMapper.deleteUser(user);
        usersList = userMapper.selectAllUsers();
    }
    
}
