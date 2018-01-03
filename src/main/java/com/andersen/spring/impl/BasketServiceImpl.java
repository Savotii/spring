package com.andersen.spring.impl;

import com.andersen.spring.controllers.BasketService;
import com.andersen.spring.dao.BasketDAO;
import com.andersen.spring.entity.Basket;
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketDAO basketDAO;

    @Override
    public void buyProduct(User user, Product product, int count) {

        Basket basket = new Basket();
        basket.setCount(count);
        basket.setProduct(product);
        basket.setUser(user);

        basketDAO.create(basket);
    }

    @Override
    public Basket update(Basket basket) {
       return basketDAO.update(basket);
    }

    @Override
    public Basket create(Basket basket) {
        return basketDAO.create(basket);
    }

    @Override
    public Basket getById(long id) {
        return basketDAO.getById(id);
    }

    @Override
    public boolean delete(long id) {
        return basketDAO.delete(id);
    }

    @Override
    public List<Basket> getAll() {
        return basketDAO.getAll();
    }

    @Override
    public List<Basket> getAllDeals(long userId) {
        return basketDAO.getAllDeals(userId);
    }

    public void setBasketDAO(BasketDAO basketDAO) {
        this.basketDAO = basketDAO;
    }
}
