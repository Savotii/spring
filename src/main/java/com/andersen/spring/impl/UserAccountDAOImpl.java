package com.andersen.spring.impl;

import com.andersen.spring.controllers.UserService;
import com.andersen.spring.dao.UserAccountDAO;
import com.andersen.spring.entity.User;
import com.andersen.spring.entity.UserAccount;
import com.andersen.spring.jdbc.MySqlHelper;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserAccountDAOImpl implements UserAccountDAO {

    private final String GET_BY_ACCOUNTID_QUERY = "SELECT * FROM ACCOUNTS WHERE ownerId = ?";
    private final String GET_BY_ID_QUERY = "SELECT * FROM ACCOUNTS WHERE id = ?";
    private final String GET_ALL = "SELECT * FROM ACCOUNTS";
    private final String DELETE_BY_ID_QUERY = "DELETE FROM ACCOUNTS WHERE id = ?";
    private final String INSERT_INTO_QUERY = "INSERT INTO ACCOUNTS(accounsNumber, amount, ownerId) VALUES(?, ?, ?)";
    private final String UPDATE_ACCOUNT = "UPDATE ACCOUNTS SET accountsNumber = ?, amount = ?, ownerId = ? WHERE id = ?";

    private MySqlHelper helper;

    private UserService userServiceImpl;

    public MySqlHelper getHelper() {
        return helper;
    }

    public void setHelper(MySqlHelper helper) {
        this.helper = helper;
    }

    @Override
    public UserAccount create(UserAccount item) {

        Connection connection = helper.createConnection();

        UserAccount ua = null;

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, item.getAccountsNumber());
            statement.setDouble(2, item.getAmount());
            statement.setLong(3, item.getUser().getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Не удалось записать данные аккаунта в базу");
            }

            ResultSet generatedSet = statement.getGeneratedKeys();
            if (generatedSet.next()) {
                item.setId(generatedSet.getLong(1));
            }

            ua = getById(item.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ua;
    }

    @Override
    public UserAccount getById(long id) {

        Connection connection = helper.createConnection();

        UserAccount ua = null;

        try {
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ua = new UserAccount();
                ua.setAmount(resultSet.getDouble("amount"));
                ua.setAccountsNumber(resultSet.getInt("accountsNumber"));
                ua.setId(resultSet.getLong("id"));
                ua.setUser(userServiceImpl.getById(resultSet.getLong("ownerId")));
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

        return ua;
    }

    @Override
    public UserAccount update(UserAccount item) {

        Connection connection = helper.createConnection();

        UserAccount ua = null;

        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT);

            statement.setInt(1, item.getAccountsNumber());
            statement.setDouble(2, item.getAmount());
            statement.setLong(3, item.getUser().getId());
            statement.setLong(4, item.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Процедура обновления счета, провалилась.");
            }

            ua = getById(item.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ua;
    }

    @Override
    public boolean delete(long id) {

        Connection connection = helper.createConnection();

        int affectRows = 0;

        boolean isOk = false;

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_BY_ID_QUERY);
            statement.setLong(1, id);

            affectRows = statement.executeUpdate();
            if (affectRows != 0)
                isOk = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isOk;
    }

    @Override
    public List<UserAccount> getAll() {
        return new LinkedList<UserAccount>();
    }

    @Override
    public List<UserAccount> getAccounts(long id) {

        Connection connection = helper.createConnection();

        UserAccount ua = null;

        List<UserAccount> userAccountList = new LinkedList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(GET_BY_ACCOUNTID_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ua = new UserAccount();
                ua.setAmount(resultSet.getDouble("amount"));
                ua.setAccountsNumber(resultSet.getInt("accountsNumber"));
                ua.setId(resultSet.getLong("id"));
                ua.setUser(userServiceImpl.getById(resultSet.getLong("ownerId")));
                userAccountList.add(ua);
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

        return userAccountList;
    }

    public UserService getUserServiceImpl() {
        return userServiceImpl;
    }

    public void setUserServiceImpl(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
}
