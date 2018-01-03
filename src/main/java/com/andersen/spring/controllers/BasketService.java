package com.andersen.spring.controllers;

import com.andersen.spring.entity.Basket;
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;

import java.util.List;

public interface BasketService {

    Basket update(Basket basket);

    Basket create(Basket basket);

    Basket getById(long id);

    boolean delete(long id);

    List<Basket> getAll();

    List<Basket> getAllDeals(long userId);

    void buyProduct(User user, Product product, int count);
}
