package com.andersen.spring.dao;

import com.andersen.spring.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    Product getProductByID(long id) throws SQLException;

    Product createProduct(Product product) throws SQLException;

    List<Product> getProducts();

    Product update(Product product) throws SQLException;

    List<Product> getProductsByTitle(String title);

    List<Product> getProductsByUserId(long userId);

    boolean deleteProductById(long id);
}
