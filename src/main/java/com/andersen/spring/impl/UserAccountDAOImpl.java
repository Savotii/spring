package com.andersen.spring.impl;

import com.andersen.spring.controllers.UserService;
import com.andersen.spring.dao.UserAccountDAO;
import com.andersen.spring.entity.User;
import com.andersen.spring.entity.UserAccount;
import com.andersen.spring.exceptions.InsufficientFunds;
import com.andersen.spring.jdbc.MySqlHelper;
import org.omg.CORBA.INTERNAL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserAccountDAOImpl implements UserAccountDAO {

    private final String GET_BY_ACCOUNTID_QUERY = "SELECT * FROM ACCOUNTS WHERE id = ?";
    private final String GET_BY_ID_QUERY = "SELECT * FROM ACCOUNTS WHERE id = ?";
    private final String GET_ALL = "SELECT * FROM ACCOUNTS WHERE ownerId = ?";
    private final String DELETE_BY_ID_QUERY = "DELETE FROM ACCOUNTS WHERE id = ?";
    private final String INSERT_INTO_QUERY = "INSERT INTO ACCOUNTS(accountsNumber, amount, ownerId) VALUES(?, ?, ?)";
    private final String UPDATE_ACCOUNT = "UPDATE ACCOUNTS SET accountsNumber = ?, amount = ?, ownerId = ? WHERE id = ?";

    private MySqlHelper helper;

    private UserService userServiceImpl;

    private DataSource dataSource;

    public MySqlHelper getHelper() {
        return helper;
    }

    public void setHelper(MySqlHelper helper) {
        this.helper = helper;
    }

    @Override
    public UserAccount create(UserAccount item) {

        UserAccount ua = null;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Object[] args = new Object[]{item.getAccountsNumber(), item.getAmount(), item.getUser().getId()};

        int out = jdbcTemplate.update(INSERT_INTO_QUERY, args);
        if (out != 0) {
            System.out.println("UserAccount saved");
        } else {
            System.out.println("UserAccount not saved");
        }

        ua = getById(item.getId());

        return ua;

        /* На время теста спрячу
        Connection connection = helper.createConnection();

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

        return ua;*/
    }

    @Override
    public UserAccount getById(long id) {

        UserAccount ua = null;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Object[] args = new Object[]{id};

        ua = jdbcTemplate.queryForObject(GET_BY_ACCOUNTID_QUERY, args, new RowMapper<UserAccount>() {
            @Override
            public UserAccount mapRow(ResultSet resultSet, int i) throws SQLException {
                UserAccount userAccount1 = new UserAccount();
                userAccount1.setAccountsNumber(resultSet.getInt("accountsNumber"));
                userAccount1.setId(resultSet.getLong("id"));
                userAccount1.setAmount(resultSet.getDouble("amount"));
                userAccount1.setUser(userServiceImpl.getById(resultSet.getLong("ownerId")));
                return userAccount1;
            }
        });

        /* На время теста
        Connection connection = helper.createConnection();

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
*/
        return ua;
    }

    @Override
    public UserAccount update(UserAccount item) {

        UserAccount ua = null;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Object[] args = new Object[]{item.getAccountsNumber(), item.getAmount(), item.getUser().getId(), item.getId()};

        int out = jdbcTemplate.update(UPDATE_ACCOUNT, args);

        if (out != 0) {
            System.out.println(" Аккаунт обновлен.");
        } else {
            System.out.println(" Не удалось обновить аккаунт.");
        }

        return getById(item.getId());

        /*
        Connection connection = helper.createConnection();

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
        }*/

        //return ua;
    }

    @Override
    public boolean delete(long id) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate.update(DELETE_BY_ID_QUERY, id) != 0;


      /*  Connection connection = helper.createConnection();

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

        return isOk;*/
    }

    @Override
    public List<UserAccount> getAll() {
        return new LinkedList<UserAccount>();
    }

    @Override
    public List<UserAccount> getAccounts(long id) {

        List<UserAccount> userAccountList = new ArrayList<UserAccount>();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Map<String, Object>> uaRows = jdbcTemplate.queryForList(GET_ALL, id);

        for (Map<String, Object> uaRow: uaRows) {

            UserAccount userAccount = new UserAccount();
            userAccount.setId(Long.parseLong(String.valueOf(uaRow.get("id"))));
            userAccount.setAmount(Double.parseDouble(String.valueOf(uaRow.get("amount"))));
            userAccount.setAccountsNumber(Integer.parseInt(String.valueOf(uaRow.get("accountsNumber"))));

            userAccount.setUser(userServiceImpl.getById(Long.parseLong(String.valueOf(uaRow.get("ownerId")))));

            userAccountList.add(userAccount);
        }

        /*Connection connection = helper.createConnection();

        UserAccount ua = null;

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
*/
        return userAccountList;
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
