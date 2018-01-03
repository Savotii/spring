package com.andersen.spring.entity;

import com.andersen.spring.impl.ProductServiceImpl;
import com.andersen.spring.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketRowMapper implements RowMapper<Basket> {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    UserServiceImpl userService;

    private ConfigurableApplicationContext ctx;

    public BasketRowMapper() {
        /*ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        productService = (ProductServiceImpl) ctx.getBean("ProductServiceImpl");
        userService = (UserServiceImpl) ctx.getBean("UserServiceImpl");*/
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

    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
}
