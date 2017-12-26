package com.andersen.spring.impl;

import com.andersen.spring.dao.ProductDAO;
import com.andersen.spring.entity.Product;
import com.andersen.spring.dao.AbstractDAO;
import com.andersen.spring.jdbc.MySqlHelper;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ProductDAOImpl extends AbstractDAO {

    private final String GET_BY_USERID_QUERY = "SELECT * FROM products WHERE userId = ?";
    private final String GET_BY_TITLE_QUERY = "SELECT * FROM products WHERE title LIKE ?";
    private final String GET_BY_ID_QUERY = "SELECT * FROM products WHERE id = ?";
    private final String GET_ALL = "SELECT * FROM products";
    private final String DELETE_ALL = "DELETE FROM products";
    private final String DELETE_BY_ID_QUERY = "DELETE FROM products WHERE id = ?";
    private final String INSERT_INTO_QUERY = "INSERT INTO products(title, description, price, userId) VALUES(?, ?, ?, ?)";
    private final String UPDATE_PRODUCT = "UPDATE products SET title = ?, description = ?, price = ?, userId =? WHERE id = ?";

    private MySqlHelper helper;

    public ProductDAOImpl() {
    }

    public void setHelper(MySqlHelper helper) {
        this.helper = helper;
    }

    public List<Product> getProductsByTitle(String title) {

        List<Product> products = new LinkedList<Product>();

        Connection connection = helper.createConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(GET_BY_TITLE_QUERY);

            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("title"), resultSet.getString("description"),
                        resultSet.getDouble("price"), resultSet.getLong("userId"));
                product.setId(resultSet.getLong("id"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        helper.closeConnection();

        return products;

    }

    public List<Product> getProductsByUserId(long userId) {

        List<Product> products = new LinkedList<Product>();

        Connection connection = helper.createConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(GET_BY_USERID_QUERY);

            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("title"), resultSet.getString("description"),
                        resultSet.getDouble("price"), resultSet.getLong("userId"));
                product.setId(resultSet.getLong("id"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        helper.closeConnection();

        return products;

    }

    public Object created(Object item) {

        Connection connection = helper.createConnection();

        Product product = (Product) item;

        Product pr = null;

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_INTO_QUERY);
            statement.setString(1, product.getTitle());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setLong(4, product.getUserId());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Добавление товара неудачно.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            }

            pr = (Product) getById(product.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        helper.closeConnection();

        return pr;
    }

    public Object getById(long id) {

        Connection connection = helper.createConnection();

        Product product = null;

        try {
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            product = new Product(resultSet.getString("title"), resultSet.getString("description"),
                    resultSet.getDouble("price"), resultSet.getLong("userId"));
            product.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        helper.closeConnection();

        return product;
    }

    public Object update(Object item) {

        Connection connection = helper.createConnection();

        Product product = (Product) item;

        Product pr = null;

        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT);

            statement.setString(1, product.getTitle());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setLong(4, product.getUserId());
            statement.setLong(5, product.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Процедура обновления товара, провалилась.");
            }

            pr = (Product) getById(product.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        helper.closeConnection();

        return pr;
    }

    public boolean delete(long id) {

        Connection connection = helper.createConnection();

        int affectRows = 0;

        boolean isOk = false;

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_BY_ID_QUERY);
            statement.setLong(1, id);

            affectRows = statement.executeUpdate();
            if (affectRows != 0)
                isOk = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        helper.closeConnection();

        return isOk;
    }

    public List<Product> getAll() {

        Connection connection = helper.createConnection();

        List<Product> products = new LinkedList<Product>();

        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("title"), resultSet.getString("description"),
                        resultSet.getDouble("price"), resultSet.getLong("userId"));
                product.setId(resultSet.getLong("id"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        helper.closeConnection();

        return products;

    }
}
