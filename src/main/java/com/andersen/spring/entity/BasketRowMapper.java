package com.andersen.spring.entity;

import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.impl.ProductServiceImpl;
import com.andersen.spring.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BasketRowMapper implements RowMapper<Basket> {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    UserServiceImpl userService;

    private static ApplicationContext ctx;

    public BasketRowMapper() {

        if (productService == null && ctx != null) {
            productService = (ProductServiceImpl) ctx.getBean("productServiceImpl");
        }

        if (userService == null && ctx != null) {
            userService = (UserServiceImpl) ctx.getBean("userServiceImpl");
        }
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

    @Autowired
    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCtx(ApplicationContext ctx) {
        if (BasketRowMapper.ctx == null)
            BasketRowMapper.ctx = ctx;
    }
}
