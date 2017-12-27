package com.andersen.spring.impl;

import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.dao.ProductDAO;
import com.andersen.spring.entity.Product;
import com.andersen.spring.impl.ProductDAOImpl;
import com.andersen.spring.dao.DAO;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    public ProductServiceImpl() {
    }

    public Product create(Product product) {
        Product pr = (Product) productDAO.created(product);
        return pr;
    }

    public Product update(Product product) {
        return (Product) productDAO.update(product);
    }

    public Product getById(long id) {
        return (Product) productDAO.getById(id);
    }

    public List<Product> getProductsByTitle(String title) {
        return productDAO.getProductsByTitle(title);
    }

    public List<Product> getProductsByUserId(long userId) {
        return productDAO.getProductsByUserId(userId);
    }

    public boolean delete(long id) {
        return productDAO.delete(id);
    }

    public List<Product> getAll() {
        return productDAO.getAll();
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product getProduct()
    {
        return new Product();
    }
}
