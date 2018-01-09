package com.andersen.spring.impl.product;

import com.andersen.spring.controllers.ProductService;
import com.andersen.spring.dao.ProductDAO;
import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    public ProductServiceImpl() {
    }

    public Product create(Product product) {
        Product pr = productDAO.create(product);
        return pr;
    }

    public Product update(Product product) {
        return (Product) productDAO.update(product);
    }

    public Product getById(long id) {
        return (Product) productDAO.getById(id);
    }

    public List<Product> getProductsByTitle(String title) {

        List<Product> productList = productDAO.getAll();

        List<Product> newProductList = new LinkedList<>();

        Pattern pattern = Pattern.compile(title + ".+");

        for (Product product : productList) {

            if (product.getTitle().indexOf(title) != -1)
                newProductList.add(product);
        }

        return newProductList;

        //return productDAO.getProductsByTitle(title);
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

    public Product getProduct() {
        return new Product();
    }

}
