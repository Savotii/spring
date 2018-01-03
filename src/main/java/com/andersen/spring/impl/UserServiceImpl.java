package com.andersen.spring.impl;

import com.andersen.spring.controllers.UserService;
import com.andersen.spring.dao.UserDAO;
import com.andersen.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public UserServiceImpl(){
    }

    public User create(User user) {
        return userDAO.create(user);
    }

    public User update(User user) {
        return userDAO.update(user);
    }

    public User getById(long id) {
        return  userDAO.getById(id);
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public List<User> getUsersByName(String name) {
        return userDAO.getUsersByName(name);
    }

    public boolean delete(long id) {
        return userDAO.delete(id);
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUser(){
        return new User();
    }
}
