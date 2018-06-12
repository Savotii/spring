package com.andersen.spring.impl.user;

import com.andersen.spring.dao.UserDAO;
import com.andersen.spring.entity.User;
import com.andersen.spring.entity.UserAccountRowMapper;
import com.andersen.spring.jdbc.MySqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserDAOImpl() {
    }

    public User create(User item) {

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
        }

        return item;
    }

    public User update(User item) {

        User user = item;

        int result = jdbcTemplate.update(UPDATE_USER, new Object[]{item.getName(), item.getEmail(), item.getPhoneNumber(),  item.getId()});
        if (result != 0) {
            user = getById(item.getId());
        }

        return user;

    }

    public boolean delete(long id) {
        return jdbcTemplate.update(DELETE_BY_ID_QUERY, id) != 0;
    }

    public List<User> getAll() {

        List<User> usersList = jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<User>(User.class));

        return usersList;

    }

    public User getById(long id) {

        User user = jdbcTemplate.queryForObject(GET_BY_ID_QUERY, new Object[]{id},  new BeanPropertyRowMapper<User>(User.class));

        return user;

    }

    public User getUserByEmail(String email) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(GET_BY_Email_QUERY, new BeanPropertyRowMapper<User>(User.class), email);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return user;

    }

    public List<User> getUsersByName(String name) {

        List<User> usersList = jdbcTemplate.query(GET_BY_NAME_QUERY, new BeanPropertyRowMapper<User>(User.class), name);

        return usersList;

    }


}
