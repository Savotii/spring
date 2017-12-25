package com.andersen.spring.facade;

import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.controllers.UserService;
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MarketStorage implements MarketFacade {

    private ProductService productService;
    private UserService userService;
    private Connection connection;

    public MarketStorage(Connection connection) {

        this.connection = connection;
        this.productService = new ProductService(connection);
        this.userService = new UserService(connection);

        try {
            this.connection = DriverManager.getConnection("com.mysql.jdbc.Driver");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User createUser(User user) {

        User nUser = null;

        try {
            nUser = userService.createUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nUser;
    }

    public User updateUser(User user) {
        return null;
    }

    public User getUserById(long id) {
        return userService.getUserById(id);

    }

    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    public List<User> getUsersByName(String name) {
        return userService.getUsersByName(name);
    }

    public boolean deleteUser(long id) {

        boolean action = false;

        try {
            action = userService.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return action;
    }

    public Product createProduct(Product product) {
        return productService.createProduct(product);
    }

    public Product updateProduct(Product product) {
        return productService.updateProduct(product);
    }

    public Product getProductById(long id) {
        return productService.getProductById(id);
    }

    public List<Product> getProductsByTitle(String title) {
        return productService.getProductsByTitle(title);
    }

    public List<Product> getProductsByUserId(long id) {
        return productService.getProductsByUserId(id);
    }

    public boolean deleteProductById(long id) {
        return productService.deleteProductById(id);
    }
}
