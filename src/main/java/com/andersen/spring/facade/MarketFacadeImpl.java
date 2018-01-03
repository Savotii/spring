package com.andersen.spring.facade;

import com.andersen.spring.controllers.AccountService;
import com.andersen.spring.controllers.BasketService;
import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.controllers.UserService;
import com.andersen.spring.entity.UserAccount;
import com.andersen.spring.impl.ProductServiceImpl;
import com.andersen.spring.impl.UserAccountServiceImpl;
import com.andersen.spring.impl.UserServiceImpl;
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MarketFacadeImpl implements MarketFacade {

    @Autowired
    private ProductService productServiceImpl;

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private AccountService userAccountImpl;

    @Autowired
    private BasketService basketServiceImpl;

    public MarketFacadeImpl(ProductServiceImpl productServiceImpl, UserServiceImpl userServiceImpl, UserAccountServiceImpl userAccountImpl) {
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

    public boolean deleteProductById(long id) {
        return productServiceImpl.delete(id);
    }

    @Override
    public void replenishTheAccount(UserAccount account, Double amount) {
        userAccountImpl.replenishTheAccount(account, amount);
    }

    @Override
    public void reduceTheAccount(UserAccount account, Double amount) {
        userAccountImpl.reduceTheAccount(account, amount);
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
    public void buyProduct(User user, Product product, int count) {
        basketServiceImpl.buyProduct(user, product, count);
    }

}
