package com.andersen.spring.impl;

import com.andersen.spring.dao.ProductDAO;
import com.andersen.spring.entity.Product;
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

    @Autowired
    private MySqlHelper helper;

    @Autowired
    private DataSource dataSource;

    public ProductDAOImpl() {
    }

    public void setHelper(MySqlHelper helper) {
        this.helper = helper;
    }

    public List<Product> getProductsByTitle(String title) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Product> productsList = jdbcTemplate.query(GET_BY_TITLE_QUERY, new BeanPropertyRowMapper<Product>(Product.class), title);

        return productsList;
       /*
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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return products;
*/
    }

    public List<Product> getProductsByUserId(long userId) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Product> products = jdbcTemplate.query(GET_BY_USERID_QUERY, new Object[]{userId}, new BeanPropertyRowMapper<Product>(Product.class));

        return products;

       /* List<Product> products = new LinkedList<Product>();

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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return products;*/

    }

    public Product create(final Product item) {

        Product product = null;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

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
            product = item;
        }

        return product;
    }

    public Product getById(long id) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Product product = jdbcTemplate.queryForObject(GET_BY_ID_QUERY, new Object[]{id}, new BeanPropertyRowMapper<Product>(Product.class));

        return product;

    }

    public Product update(Product item) {

        Product product = item;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int result = jdbcTemplate.update(UPDATE_PRODUCT, new Object[]{item.getTitle(), item.getDescription(), item.getPrice(), item.getUserId(), item.getId()});
        if (result != 0) {
            product = getById(item.getId());
        }

        return product;

    }

    public boolean delete(long id) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate.update(DELETE_BY_ID_QUERY, id) != 0;

    }

    public List<Product> getAll() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Product> productsList = jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<Product>(Product.class));

        return productsList;

    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}

