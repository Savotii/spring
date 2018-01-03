package com.andersen.spring.entity;

import com.andersen.spring.dao.UserDAO;
import com.andersen.spring.impl.UserDAOImpl;
import com.andersen.spring.impl.UserServiceImpl;
import com.andersen.spring.jdbc.MySqlHelper;
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
        userAccount.setUserId(resultSet.getLong("ownerId"));
        //Тут фигня. не прокидывает юзерсервис..
       // userAccount.setUser(userServiceImpl.getById(resultSet.getLong("ownerId")));

        return userAccount;
    }

    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

}
