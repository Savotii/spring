package com.andersen.spring.storage;

import com.andersen.spring.entity.Product;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MarketStorage {

    private List<Product> products = new LinkedList<Product>();

    private final String FILEPATH = "products.txt";

    public void init() {

        File file = new File(FILEPATH);

        Gson gson = new Gson();

        if (!file.exists()) {
            initFileProducts(file);
        }

        //Читаем файл
        if (file.exists()) {
            List<String> lines = new LinkedList<>();

            try {
                lines = Files.readAllLines(Paths.get(FILEPATH));
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (String line : lines) {
              Product product = gson.fromJson(line, Product.class);
              products.add(product);
            }
        }

    }

    public void destroy() {

    }

    public Product createProduct(Product product)
    {
        Product p = product;

        products.add(product);

        return p;
    }


    private void initFileProducts(File file) {

        FileWriter fw = null;

        try {
            fw = new FileWriter(FILEPATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();

        String title = "Продукт_";
        String description = "Описание товара_";
        long userId = 1;
        int count = 10;

        for (int i = 0; i < 150; i++) {

            Product product = new Product(title + i, description + i, 10.2D, userId);

            try {
                fw.write(gson.toJson(product) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(product);
        }

        try {

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
