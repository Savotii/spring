package com.andersen.spring.Controllers;

import com.andersen.spring.entity.Product;
import com.andersen.spring.impl.ProductDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductService {

    private ProductDAOImpl productDAO;
    private Connection connection;

    public ProductService(Connection connection)
    {
        this.connection = connection;
        this.productDAO = new ProductDAOImpl(connection);
    }

    public Product createProduct(Product product)
    {
        Product pr = null;
        try {
            pr = productDAO.createProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pr;
    }

    public Product updateProduct(Product product)
    {
       return productDAO.update(product);
    }

    public Product getProductById(long id)
    {
        return productDAO.getProductByID(id);
    }

    public List<Product> getProductsByTitle(String title)
    {
        return productDAO.getProductsByTitle(title);
    }

    public List<Product> getProductsByUserId(long userId)
    {
        return productDAO.getProductsByUserId(userId);
    }

    public boolean deleteProductById(long id)
    {
        return productDAO.deleteProductById(id);
    }
}
