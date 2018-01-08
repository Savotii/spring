package com.andersen.spring.impl;

import com.andersen.spring.dao.ProductDAO;
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.ProductRowMapper;
import com.andersen.spring.jdbc.MySqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import javax.sql.rowset.JdbcRowSet;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProductDAOImpl implements ProductDAO {

    private final String GET_BY_USERID_QUERY = "SELECT * FROM products WHERE userId = ?";
    private final String GET_BY_TITLE_QUERY = "SELECT * FROM products WHERE title LIKE ?";
    private final String GET_BY_ID_QUERY = "SELECT * FROM products WHERE id = ?";
    private final String GET_ALL = "SELECT * FROM products";
    private final String DELETE_ALL = "DELETE FROM products";
    private final String DELETE_BY_ID_QUERY = "DELETE FROM products WHERE id = ?";
    private final String INSERT_INTO_QUERY = "INSERT INTO products(title, description, price, userId) VALUES(?, ?, ?, ?)";
    private final String UPDATE_PRODUCT = "UPDATE products SET title = ?, description = ?, price = ?, userId =? WHERE id = ?";

    private DataSource dataSource;

    @Autowired
    ProductRowMapper productRowMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ProductDAOImpl() {
    }

    public List<Product> getProductsByTitle(String title) {

        List<Product> productsList = jdbcTemplate.query(GET_BY_TITLE_QUERY, productRowMapper, title);

        return productsList;

    }

    public List<Product> getProductsByUserId(long userId) {

        List<Product> products = jdbcTemplate.query(GET_BY_USERID_QUERY, new Object[]{userId}, productRowMapper);//new BeanPropertyRowMapper<Product>(Product.class));

        return products;

    }

    public Product create(Product item) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(new PreparedStatementCreator() {
                                             @Override
                                             public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                                                 PreparedStatement statement = connection.prepareStatement(INSERT_INTO_QUERY, Statement.RETURN_GENERATED_KEYS);
                                                 statement.setString(1, item.getTitle());
                                                 statement.setString(2, item.getDescription());
                                                 statement.setDouble(3, item.getPrice());
                                                 statement.setLong(4, item.getUserId());
                                                 return statement;
                                             }
                                         },
                keyHolder
        );

        if (result != 0) {
            item.setId(keyHolder.getKey().longValue());
        }

        return item;
    }

    public Product getById(long id) {

        Product product = jdbcTemplate.queryForObject(GET_BY_ID_QUERY, new Object[]{id}, productRowMapper); //new BeanPropertyRowMapper<Product>(Product.class));

        return product;

    }

    public Product update(Product item) {

        Product product = item;

        int result = jdbcTemplate.update(UPDATE_PRODUCT, new Object[]{item.getTitle(), item.getDescription(), item.getPrice(), item.getUserId(), item.getId()});
        if (result != 0) {
            product = getById(item.getId());
        }

        return product;

    }

    public boolean delete(long id) {

        return jdbcTemplate.update(DELETE_BY_ID_QUERY, id) != 0;

    }

    public List<Product> getAll() {

        List<Product> productsList = jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<Product>(Product.class));

        return productsList;

    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}

