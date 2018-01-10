package com.andersen.spring.facade;

import com.andersen.spring.controllers.AccountService;
import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.controllers.UserService;
import com.andersen.spring.entity.UserAccount;
import com.andersen.spring.impl.product.ProductServiceImpl;
import com.andersen.spring.impl.userAccount.UserAccountServiceImpl;
import com.andersen.spring.impl.user.UserServiceImpl;
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

public class MarketFacadeImpl implements MarketFacade {

    private ProductService productServiceImpl;

    private UserService userServiceImpl;

    private AccountService userAccountImpl;

    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    public MarketFacadeImpl(ProductService productServiceImpl, UserService userServiceImpl, AccountService userAccountImpl) {
        this.productServiceImpl = productServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.userAccountImpl = userAccountImpl;
    }

    public User createUser(User user) {
        return userServiceImpl.create(user);
    }

    public User updateUser(User user) {
        return userServiceImpl.update(user);
    }

    public User getUserById(long id) {
        return userServiceImpl.getById(id);
    }

    public User getUserByEmail(String email) {
        return userServiceImpl.getUserByEmail(email);
    }

    public List<User> getUsersByName(String name) {
        return userServiceImpl.getUsersByName(name);
    }

    public boolean deleteUser(long id) {
        return userServiceImpl.delete(id);
    }

    public Product createProduct(Product product) {
        return productServiceImpl.create(product);
    }

    public Product updateProduct(Product product) {
        return productServiceImpl.update(product);
    }

    public Product getProductById(long id) {
        return productServiceImpl.getById(id);
    }

    public List<Product> getProductsByTitle(String title) {
        return productServiceImpl.getProductsByTitle(title);
    }

    public List<Product> getProductsByUserId(long id) {
        return productServiceImpl.getProductsByUserId(id);
    }

    public List<Product> getAllProducts()
    {
        return productServiceImpl.getAll();
    }

    public boolean deleteProductById(long id) {
        return productServiceImpl.delete(id);
    }

    @Override
    public UserAccount create(UserAccount account) {
        return userAccountImpl.create(account);
    }

    @Override
    public UserAccount getById(long id) {
        return userAccountImpl.getById(id);
    }

    @Override
    public boolean deleteUserAccount(long id) {
        return userAccountImpl.delete(id);
    }

    @Override
    public List<UserAccount> getAll() {
        return userAccountImpl.getAll();
    }


    @Transactional
    public void buyProduct(User user, Product product, UserAccount buyerAccount, UserAccount sellerAccount) {

        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    userAccountImpl.buyProduct(user, product, buyerAccount, sellerAccount);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
     /*
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            userAccountImpl.buyProduct(user, product, buyerAccount, sellerAccount);
            transactionManager.commit(txStatus);
        }
        catch (InsufficientFunds e)
        {
            e.printStackTrace();
            transactionManager.rollback(txStatus);
        }*/
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void updateBalance(UserAccount userAccount, Double amount)
    {
        userAccountImpl.updateBalance(userAccount, amount);
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

}
