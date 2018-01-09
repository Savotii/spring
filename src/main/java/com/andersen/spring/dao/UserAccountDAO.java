package com.andersen.spring.dao;

import com.andersen.spring.entity.UserAccount;

import java.util.List;

public interface UserAccountDAO extends DAO<UserAccount> {

    List<UserAccount> getAccounts(long id);

    UserAccount updateBalance(UserAccount userAccount, Double amount);

    List<UserAccount> getAccountsByUserId(long userId);
}
