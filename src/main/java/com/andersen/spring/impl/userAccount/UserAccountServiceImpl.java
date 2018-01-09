package com.andersen.spring.impl.userAccount;

import com.andersen.spring.controllers.AccountService;
import com.andersen.spring.dao.UserAccountDAO;
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;
import com.andersen.spring.entity.UserAccount;
import com.andersen.spring.exceptions.InsufficientFunds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserAccountServiceImpl implements AccountService {

    @Autowired
    private UserAccountDAO userAccountDAO;

    @Override
    public void updateBalance(UserAccount account, Double amount) {

        UserAccount updAccount = userAccountDAO.getById(account.getId());

        try {
            checkMoney(account, amount);
            userAccountDAO.updateBalance(updAccount, amount);
        } catch (InsufficientFunds insufficientFunds) {
            insufficientFunds.printStackTrace();
        }
    }

    public boolean checkMoney(UserAccount userAccount, Double amount) throws InsufficientFunds {

        if (userAccount.getAmount() == null || userAccount.getAmount() < amount)
            throw new InsufficientFunds("На счету недостаточно денег.");

        return true;
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

    @Override
    public void buyProduct(User user, Product product, UserAccount buyerAccount, UserAccount sellerAccount) throws InsufficientFunds {

        User seller = product.getUser();

        if (checkMoney(buyerAccount, product.getPrice())) {
            buyerAccount = userAccountDAO.updateBalance(buyerAccount, -product.getPrice());
        }

        //2. increase to seller
        throw new InsufficientFunds();
       // sellerAccount = userAccountDAO.updateBalance(sellerAccount, product.getPrice());
    }
}
