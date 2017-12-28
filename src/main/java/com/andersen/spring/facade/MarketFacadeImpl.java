package com.andersen.spring.facade;

import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.controllers.UserService;
import com.andersen.spring.entity.UserAccount;
import com.andersen.spring.impl.ProductServiceImpl;
import com.andersen.spring.impl.UserServiceImpl;
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;
import com.andersen.spring.jdbc.MySqlHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MarketFacadeImpl implements MarketFacade {

    private ProductService productServiceImpl;
    private UserService userServiceImpl;

    public MarketFacadeImpl(ProductServiceImpl productServiceImpl, UserServiceImpl userServiceImpl) {
        this.productServiceImpl = productServiceImpl;
        this.userServiceImpl = userServiceImpl;

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
        //TO DO
    }

    @Override
    public void reduceTheAccount(UserAccount account, Double amount) {
        //TO DO
    }
}
