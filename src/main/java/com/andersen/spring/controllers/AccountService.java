package com.andersen.spring.controllers;

import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;
import com.andersen.spring.entity.UserAccount;
import com.andersen.spring.exceptions.InsufficientFunds;

import java.util.List;

public interface AccountService {

    void updateBalance(UserAccount account, Double amount);

    UserAccount create(UserAccount account);

    UserAccount getById(long id);

    boolean delete(long id);

    List<UserAccount> getAll(long id);

    void buyProduct(User user, Product product, UserAccount buyerAccount, UserAccount sellerAccount);
}
