package com.andersen.spring.dao;

import com.andersen.spring.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    Product getByID(long id) throws SQLException;

    void insert(Product product) throws SQLException;

    List<Product> getAll();

    void update(Product product) throws SQLException;

    void deleteByID(long id) throws SQLException;

    void deleteAll() throws SQLException;

}
