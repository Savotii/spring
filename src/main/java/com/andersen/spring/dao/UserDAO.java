package com.andersen.spring.dao;

import com.andersen.spring.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void createUser(User user) throws SQLException;

    void updateUser(User user) throws SQLException;

    User getUserById(long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

    boolean deleteUser(long id) throws SQLException;

    List<User> getAllUsers();
}
