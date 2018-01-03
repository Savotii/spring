package com.andersen.spring.dao;

import com.andersen.spring.entity.Basket;

import java.util.List;

public interface BasketDAO extends DAO<Basket> {
    List<Basket> getAllDeals(long userId);
}
