package com.example.model.entity;

public class OrderRow extends Entity{

    private long orderId;
    private long productId;
    private int count;


    public long getOrderId() {return orderId;}
    public void setOrderId(long orderId) {this.orderId = orderId;}
    public long getProductId() {return productId;}
    public void setProductId(long productId) {this.productId = productId;}
    public int getCount() {return count;}
    public void setCount(int count) {this.count = count;}

    public OrderRow(){}
}
