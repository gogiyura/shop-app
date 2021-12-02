package com.example.model;

import com.example.model.bean.Product;

public class ProductSpecificationWhere extends ProductSpecification {
    @Override
    public boolean specified(Product product) {
        return super.specified(product);
    }

    @Override
    public String toSqlClauses() {
        return super.toSqlClauses();
    }
}
