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

    @Autowired
    private MySqlHelper helper;

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private DataSource dataSource;

    public MySqlHelper getHelper() {
        return helper;
    }

    public void setHelper(MySqlHelper helper) {
        this.helper = helper;
    }

    @Override
    public UserAccount create(UserAccount item) {

        UserAccount ua = item;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

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
            ua.setId(keyHolder.getKey().longValue());
        }
        else {
            ua = null;
        }

        return ua;
        //INSERT_INTO_QUERY, new Object[]{item.getAccountsNumber(), item.getAmount(), item.getUser().getId()});

        /*//Решить вопрос с получением ID из базы

        if (out != 0) {
            System.out.println("UserAccount saved");
          *//*  item.setId(keyHolder.getKey().longValue());
            ua = getById(item.getId());*//*
        } else {
            System.out.println("UserAccount not saved");
        */
  //  }

    // return ua;

}

    @Override
    public UserAccount getById(long id) {

        UserAccount ua = null;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        ua = jdbcTemplate.queryForObject(GET_BY_ACCOUNTID_QUERY, new Object[]{id}, new UserAccountRowMapper());
        //ua = jdbcTemplate.queryForObject(GET_BY_ACCOUNTID_QUERY,  new Object[]{id}, new BeanPropertyRowMapper<UserAccount>(UserAccount.class));

        return ua;
    }

    @Override
    public UserAccount update(UserAccount item) {

        UserAccount ua = null;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

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

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate.update(DELETE_BY_ID_QUERY, id) != 0;

    }

    @Override
    public List<UserAccount> getAll() {
        return new LinkedList<UserAccount>();
    }

    @Override
    public List<UserAccount> getAccounts(long id) {

        //List<UserAccount> userAccountList = new ArrayList<UserAccount>();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<UserAccount> userAccounts = jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<UserAccount>(UserAccount.class), id);

        /*List<Map<String, Object>> uaRows = jdbcTemplate.queryForList(GET_ALL, id);

        for (Map<String, Object> uaRow: uaRows) {

            UserAccount userAccount = new UserAccount();
            userAccount.setId(Long.parseLong(String.valueOf(uaRow.get("id"))));
            userAccount.setAmount(Double.parseDouble(String.valueOf(uaRow.get("amount"))));
            userAccount.setAccountsNumber(Integer.parseInt(String.valueOf(uaRow.get("accountsNumber"))));

            userAccount.setUser(userServiceImpl.getById(Long.parseLong(String.valueOf(uaRow.get("ownerId")))));

            userAccountList.add(userAccount);
        }*/

        return userAccounts;//userAccountList;
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
