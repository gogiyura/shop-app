package com.example.model;

import com.example.model.bean.OrderRow;

public class OrdenRowSpecification extends AbstractSpecification<OrderRow> {

    @Override
    public boolean specified(OrderRow orderRow) {
        if (orderRow != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toSqlClauses() {
        String result = "SELECT * FROM order-row";
        return super.toSqlClauses();
    }
}
