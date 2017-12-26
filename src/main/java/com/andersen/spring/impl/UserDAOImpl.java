package com.andersen.spring.impl;

import com.andersen.spring.dao.UserDAO;
import com.andersen.spring.entity.User;
import com.andersen.spring.dao.AbstractDAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

     private Connection connection;

    private final String GET_BY_NAME_QUERY = "SELECT * FROM users WHERE name LIKE ?";
    private final String GET_BY_Email_QUERY = "SELECT * FROM users WHERE email = ?";
    private final String GET_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";

    private final String GET_ALL = "SELECT * FROM users";
    private final String DELETE_ALL = "DELETE * FROM users";
    private final String DELETE_BY_ID_QUERY = "DELETE * FROM users WHERE id = ?";
    private final String INSERT_INTO_QUERY = "INSERT INTO users(name, email, phoneNumber) VALUES( ?, ?, ?)";
    private final String UPDATE_USER = "UPDATE products SET name = ?, email = ?, phoneNumber =? WHERE id = ?";


    public UserDAOImpl(){//(Connection connection) {
        //this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public User getUserByEmail(String email) {

        User user = null;

        try {
            PreparedStatement statement = connection.prepareStatement(GET_BY_Email_QUERY);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("phoneNumber"));
                user.setId(resultSet.getLong("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getUsersByName(String name) {

        List<User> users = new LinkedList<User>();

        try {
            PreparedStatement statement = connection.prepareStatement(GET_BY_NAME_QUERY);

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("phoneNumber"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public Object created(Object item) {

        User user = (User) item;

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_QUERY);

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhoneNumber());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Не удалось записать пользователя в базу");
            }

            ResultSet generatedSet = statement.getGeneratedKeys();
            if (generatedSet.next()) {
                user.setId(generatedSet.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Object getById(long id) {

        User user = null;

        try {
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("phoneNumber"));
                user.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Object update(Object item) {

        User user = (User) item;

        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhoneNumber());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Не удалось обновить данные пользователя.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (User) getById(user.getId());
    }

    public boolean delete(long id) {

        boolean affectedAction = false;

        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY);
            statement.setLong(1, id);

            affectedAction = statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affectedAction;
    }
}
