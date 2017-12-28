package com.andersen.spring.controllers;

import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.UserAccount;
import com.andersen.spring.exceptions.InsufficientFunds;

import java.util.List;

public interface AccountService {

    void replenishTheAccount(UserAccount account, Double amount);

    void reduceTheAccount(UserAccount account, Double amount);

    UserAccount create(UserAccount account);

    UserAccount getById(long id);

    boolean delete(long id);

    List<UserAccount> getAll();
}
