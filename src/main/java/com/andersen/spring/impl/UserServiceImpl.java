package com.andersen.spring.impl;

import com.andersen.spring.controllers.UserService;
import com.andersen.spring.entity.User;
import com.andersen.spring.impl.UserDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAOImpl userDAO;
    private Connection connection;

    public UserServiceImpl(Connection connection) {
        this.connection = connection;
        this.userDAO = new UserDAOImpl(connection);
    }

    public User create(User user) {
        return (User) userDAO.created(user);
    }

    public User update(User user) {
        return (User) userDAO.update(user);
    }

    public User getById(long id) {
        return (User) userDAO.getById(id);
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

}
