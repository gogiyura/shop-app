package com.example.model.bean;

import java.io.Serializable;

public class OrderRow implements Serializable {

    private long id;
    private long orderId;
    private long productId;
    private int count;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public OrderRow(){}
    public OrderRow(long id, long orderId, long productId, int count) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderRow{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", count=" + count +
                '}';
    }
}