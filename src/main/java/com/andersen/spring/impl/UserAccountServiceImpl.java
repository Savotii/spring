package com.andersen.spring.impl;

import com.andersen.spring.controllers.AccountService;
import com.andersen.spring.dao.UserAccountDAO;
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.UserAccount;
import com.andersen.spring.exceptions.InsufficientFunds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserAccountServiceImpl implements AccountService {

    @Autowired
    private UserAccountDAO userAccountDAO;

    @Override
    public void replenishTheAccount(UserAccount account, Double amount) {
        UserAccount updAccount = userAccountDAO.getById(account.getId());
        updAccount.replenishTheAccount(amount);
    }

    @Override
    public void reduceTheAccount(UserAccount account, Double amount) {

        UserAccount updAccount = userAccountDAO.getById(account.getId());

        try {
            updAccount.reduceTheAccount(amount);
            userAccountDAO.update(updAccount);
        } catch (InsufficientFunds insufficientFunds) {
            insufficientFunds.printStackTrace();
        }
    }

    @Override
    public UserAccount create(UserAccount account) {
        return userAccountDAO.create(account);
    }

    @Override
    public UserAccount getById(long id) {
        return userAccountDAO.getById(id);
    }

    @Override
    public boolean delete(long id) {
        return userAccountDAO.delete(id);
    }

    @Override
    public List<UserAccount> getAll() {
        return userAccountDAO.getAll();
    }

    public void setUserAccountDAO(UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }

}
