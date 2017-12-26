package com.andersen.spring.facade;

import com.andersen.spring.controllers.ProductService;
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

    private ProductServiceImpl productServiceImpl;
    private UserServiceImpl userServiceImpl;
    private Connection connection;

    public MarketFacadeImpl(ProductServiceImpl productServiceImpl, UserServiceImpl userServiceImpl) {

        this.connection = MySqlHelper.createConnection();
        this.productServiceImpl = productServiceImpl;//new ProductServiceImpl();
        this.userServiceImpl = userServiceImpl;//new UserServiceImpl();

    }

    public User createUser(User user) {

        return userServiceImpl.create(user);
    }

    public User updateUser(User user) {
        return null;
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

        boolean action = false;

        action = userServiceImpl.delete(id);

        return action;
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
}
