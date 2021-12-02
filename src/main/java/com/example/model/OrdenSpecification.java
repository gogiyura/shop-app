package com.example.model;

import com.example.model.bean.Order;

public class OrdenSpecification extends AbstractSpecification<Order> {

    @Override
    public boolean specified(Order order) {
        return true;
    }

    @Override
    public String toSqlClauses() {
        String result = "SELECT * FROM order";
        return result;
    }
}
