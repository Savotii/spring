package com.andersen.spring.controllers;

import com.andersen.spring.entity.User;
import com.andersen.spring.impl.UserDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService{

    private UserDAOImpl userDAO;
    private Connection connection;

    public UserService(Connection connection){
        this.connection = connection;
        this.userDAO = new UserDAOImpl(connection);
    }

    public User createUser(User user) throws SQLException {
       return userDAO.createUser(user);
    }

    public void updateUser(User user) throws SQLException {
        userDAO.updateUser(user);
    }

    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public List<User> getUsersByName(String name) {
        return userDAO.getUsersByName(name);
    }

    public boolean deleteUser(long id) throws SQLException {
        return userDAO.deleteUser(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
