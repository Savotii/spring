package com.andersen.spring.dao;

import com.andersen.spring.entity.UserAccount;

import java.util.List;

public interface UserAccountDAO extends DAO<UserAccount> {

    List<UserAccount> getAccount(long id);
}
