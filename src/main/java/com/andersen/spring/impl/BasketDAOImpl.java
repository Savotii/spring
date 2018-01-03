package com.andersen.spring.impl;

import com.andersen.spring.dao.BasketDAO;
import com.andersen.spring.entity.Basket;
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;
import com.andersen.spring.jdbc.MySqlHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;

public class BasketDAOImpl implements BasketDAO {


    @Autowired
    private MySqlHelper helper;

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Basket> getAllDeals(long userId) {
        return null;
    }

    @Override
    public Basket create(Basket item) {
        return null;
    }

    @Override
    public Basket getById(long id) {
        return null;
    }

    @Override
    public Basket update(Basket item) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Basket> getAll() {
        return null;
    }

    public void setHelper(MySqlHelper helper) {
        this.helper = helper;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
