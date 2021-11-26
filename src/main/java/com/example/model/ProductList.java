package com.example.model;

import com.example.model.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private static final List<Product> productList = new ArrayList();

    private ProductList() {}

    public static List<Product> getInstance() {
        return productList;
    }

    static {
        //productList.add(new Product());
    }
}
