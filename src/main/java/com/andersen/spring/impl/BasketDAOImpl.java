package com.andersen.spring.impl;

import com.andersen.spring.dao.BasketDAO;
import com.andersen.spring.entity.Basket;
import com.andersen.spring.entity.BasketRowMapper;
import com.andersen.spring.jdbc.MySqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BasketDAOImpl implements BasketDAO {

    private final String GET_BY_USERID_QUERY = "SELECT * FROM basket WHERE userId = ?";
    private final String GET_BY_ID_QUERY = "SELECT * FROM basket WHERE id = ? ";
    private final String GET_ALL = "SELECT * FROM basket where userId = ?";
    private final String GET_ALL_FROM_TABLE = "SELECT * FROM basket";
    private final String DELETE_ALL = "DELETE FROM basket where userId = ?";
    private final String DELETE_BY_ID_QUERY = "DELETE FROM basket WHERE id = ?";
    private final String INSERT_INTO_QUERY = "INSERT INTO basket(userId, productId, count) VALUES(?, ?, ? )";
    private final String UPDATE_PRODUCT = "UPDATE basket SET userId = ?, productId = ?, count = ? WHERE id = ?";

    @Autowired
    private MySqlHelper helper;

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Basket> getAllDeals(long userId) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Basket> basketList = jdbcTemplate.query(GET_ALL, new Object[]{userId}, new BeanPropertyRowMapper<Basket>(Basket.class));

        return basketList;

    }

    @Override
    public Basket create(Basket item) {

        Basket basket = null;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(new PreparedStatementCreator() {
                                             @Override
                                             public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                                                 PreparedStatement statement = connection.prepareStatement(INSERT_INTO_QUERY, Statement.RETURN_GENERATED_KEYS);
                                                 statement.setLong(1, item.getUser().getId());
                                                 statement.setLong(2, item.getProduct().getId());
                                                 statement.setInt(3, item.getCount());
                                                 return statement;
                                             }
                                         },
                keyHolder
        );

        if (result != 0) {
            item.setId(keyHolder.getKey().longValue());
            basket = item;
        }

        return basket;
    }

    @Override
    public Basket getById(long id) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Basket basket = jdbcTemplate.queryForObject(GET_BY_ID_QUERY, new Object[]{id}, new BasketRowMapper());//new BeanPropertyRowMapper<Basket>(Basket.class));

        return basket;

    }

    @Override
    public Basket update(Basket item) {

        Basket basket = item;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int result = jdbcTemplate.update(UPDATE_PRODUCT, new Object[]{item.getUser().getId(), item.getProduct().getId(), item.getCount() , item.getId()});
        if (result != 0) {
            basket = getById(item.getId());
        }

        return basket;

    }

    @Override
    public boolean delete(long id) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate.update(DELETE_BY_ID_QUERY, id) != 0;

    }

    @Override
    public List<Basket> getAll() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Basket> basketList = jdbcTemplate.query(GET_ALL_FROM_TABLE, new BeanPropertyRowMapper<Basket>(Basket.class));

        return basketList;

    }

    public void setHelper(MySqlHelper helper) {
        this.helper = helper;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
