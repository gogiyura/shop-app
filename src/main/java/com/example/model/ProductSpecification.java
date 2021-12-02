package com.example.model;

import com.example.model.bean.Product;

public class ProductSpecification extends AbstractSpecification<Product>{
    @Override
    public boolean specified(Product product) {
        if (product == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toSqlClauses() {
        String res = "SELECT * FROM product";
        return res;
    }
}
