package com.andersen.spring.impl;

import com.andersen.spring.dao.UserDAO;
import com.andersen.spring.entity.User;
import com.andersen.spring.jdbc.MySqlHelper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final String GET_BY_NAME_QUERY = "SELECT * FROM users WHERE name LIKE ?";
    private final String GET_BY_Email_QUERY = "SELECT * FROM users WHERE email = ?";
    private final String GET_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";

    private final String GET_ALL = "SELECT * FROM users";
    private final String DELETE_ALL = "DELETE FROM users";
    private final String DELETE_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";
    private final String INSERT_INTO_QUERY = "INSERT INTO users(name, email, phoneNumber) VALUES( ?, ?, ?)";
    private final String UPDATE_USER = "UPDATE users SET name = ?, email = ?, phoneNumber =? WHERE id = ?";

    private MySqlHelper helper;

    private DataSource dataSource;

    public UserDAOImpl() {
    }

    public void setHelper(MySqlHelper helper) {
        this.helper = helper;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User create(User item) {

        User user = null;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(new PreparedStatementCreator() {
                                             @Override
                                             public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                                                 PreparedStatement statement = connection.prepareStatement(INSERT_INTO_QUERY, Statement.RETURN_GENERATED_KEYS);
                                                 statement.setString(1, item.getName());
                                                 statement.setString(2, item.getEmail());
                                                 statement.setString(3, item.getPhoneNumber());
                                                 return statement;
                                             }
                                         },
                keyHolder
        );

        if (result != 0) {
            item.setId(keyHolder.getKey().longValue());
            user = item;
        }

        return user;
        /*
        Connection connection = helper.createConnection();

        User user = (User) item;

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_QUERY, Statement.RETURN_GENERATED_KEYS);

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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;*/
    }

    public User update(User item) {

        User user = item;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int result = jdbcTemplate.update(UPDATE_USER, new Object[]{item.getName(), item.getEmail(), item.getPhoneNumber(),  item.getId()});
        if (result != 0) {
            user = getById(item.getId());
        }

        return user;

      /*  Connection connection = helper.createConnection();

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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return (User) getById(user.getId());*/
    }

    public boolean delete(long id) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate.update(DELETE_BY_ID_QUERY, id) != 0;
    }

    public List<User> getAll() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<User> usersList = jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<User>(User.class));

        return usersList;
/*
        Connection connection = helper.createConnection();

        List<User> users = new LinkedList<User>();

        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("phoneNumber"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;*/
    }

    public User getById(long id) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        User user = jdbcTemplate.queryForObject(GET_BY_ID_QUERY, new Object[]{id},  new BeanPropertyRowMapper<User>(User.class));

        return user;

       /* Connection connection = helper.createConnection();

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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;*/
    }

    public User getUserByEmail(String email) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        User user = jdbcTemplate.queryForObject(GET_BY_Email_QUERY, new BeanPropertyRowMapper<User>(User.class), email);

        return user;
        /*
        Connection connection = helper.createConnection();

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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;*/
    }

    public List<User> getUsersByName(String name) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<User> usersList = jdbcTemplate.query(GET_BY_NAME_QUERY, new BeanPropertyRowMapper<User>(User.class), name);

        return usersList;
/*
        Connection connection = helper.createConnection();

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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;*/
    }


}
