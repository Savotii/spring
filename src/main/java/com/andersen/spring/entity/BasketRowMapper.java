package com.andersen.spring.entity;

import com.andersen.spring.impl.ProductServiceImpl;
import com.andersen.spring.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BasketRowMapper implements RowMapper<Basket> {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    UserServiceImpl userService;

    public BasketRowMapper() {

    }

    @Override
    public Basket mapRow(ResultSet resultSet, int i) throws SQLException {
        Basket basket = new Basket();
        basket.setId(resultSet.getLong("id"));
        basket.setUser(userService.getById(resultSet.getLong("userId")));
        basket.setProduct(productService.getById(resultSet.getLong("productId")));
        basket.setCount(resultSet.getInt("count"));

        return basket;
    }

}
