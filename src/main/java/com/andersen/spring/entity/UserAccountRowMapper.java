package com.andersen.spring.entity;

import com.andersen.spring.impl.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountRowMapper implements RowMapper<UserAccount> {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public UserAccount mapRow(ResultSet resultSet, int i) throws SQLException {

        UserAccount userAccount = new UserAccount();
        userAccount.setAccountsNumber(resultSet.getInt("accountsNumber"));
        userAccount.setAmount(resultSet.getDouble("amount"));
        userAccount.setId(resultSet.getLong("id"));
        userAccount.setUser(userServiceImpl.getById(resultSet.getLong("ownerId")));

        return userAccount;
    }

    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

}
