package com.andersen.spring.impl;

import com.andersen.spring.controllers.UserService;
import com.andersen.spring.dao.UserAccountDAO;
import com.andersen.spring.entity.UserAccount;
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
import java.util.*;

public class UserAccountDAOImpl implements UserAccountDAO {

    private final String GET_BY_ACCOUNTID_QUERY = "SELECT * FROM ACCOUNTS WHERE id = ?";
    private final String GET_BY_ID_QUERY = "SELECT * FROM ACCOUNTS WHERE id = ?";
    private final String GET_ALL = "SELECT * FROM ACCOUNTS WHERE ownerId = ?";
    private final String DELETE_BY_ID_QUERY = "DELETE FROM ACCOUNTS WHERE id = ?";
    private final String INSERT_INTO_QUERY = "INSERT INTO ACCOUNTS(accountsNumber, amount, ownerId) VALUES(?, ?, ?)";
    private final String UPDATE_ACCOUNT = "UPDATE ACCOUNTS SET accountsNumber = ?, amount = ?, ownerId = ? WHERE id = ?";

    private UserService userServiceImpl;

    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    UserAccountRowMapper userAccountRowMapper;

    @Override
    public UserAccount create(UserAccount item) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(new PreparedStatementCreator() {
                                @Override
                                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                                    PreparedStatement statement = connection.prepareStatement(INSERT_INTO_QUERY, Statement.RETURN_GENERATED_KEYS);
                                    statement.setInt(1, item.getAccountsNumber());
                                    statement.setDouble(2, item.getAmount());
                                    statement.setLong(3, item.getUser().getId());
                                    return statement;
                                }
                            },
                keyHolder
        );

        if(result != 0) {
            item.setId(keyHolder.getKey().longValue());
        }

        return item;

}

    @Override
    public UserAccount getById(long id) {

        UserAccount ua = null;

        ua = jdbcTemplate.queryForObject(GET_BY_ACCOUNTID_QUERY, new Object[]{id}, userAccountRowMapper);

        return ua;
    }

    @Override
    public UserAccount update(UserAccount item) {

        UserAccount ua = null;

        int out = jdbcTemplate.update(UPDATE_ACCOUNT, new Object[]{item.getAccountsNumber(), item.getAmount(), item.getUser().getId(), item.getId()});

        if (out != 0) {
            System.out.println(" Аккаунт обновлен.");
        } else {
            System.out.println(" Не удалось обновить аккаунт.");
        }

        return getById(item.getId());

    }

    @Override
    public boolean delete(long id) {

        return jdbcTemplate.update(DELETE_BY_ID_QUERY, id) != 0;

    }

    @Override
    public List<UserAccount> getAll() {
        return new LinkedList<UserAccount>();
    }

    @Override
    public List<UserAccount> getAccounts(long id) {

        List<UserAccount> userAccounts = jdbcTemplate.query(GET_ALL, userAccountRowMapper);

        return userAccounts;
    }

    public UserService getUserServiceImpl() {
        return userServiceImpl;
    }

    public void setUserServiceImpl(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
