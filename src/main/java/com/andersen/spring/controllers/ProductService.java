package com.andersen.spring.controllers;

import com.andersen.spring.entity.Product;

import java.util.List;

public interface ProductService {

    Product update(Product product);

    Product create(Product product);

    Product getById(long id);

    boolean delete(long id);

    List<Product> getAll();

}
