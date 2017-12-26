package com.andersen.spring.dao;

import com.andersen.spring.entity.Product;

import java.util.List;

public interface ProductDAO extends DAO<Product> {

    List<Product> getProductsByTitle(String title);

    List<Product> getProductsByUserId(long userId);

}
