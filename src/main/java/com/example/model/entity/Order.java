package com.example.model.entity;

import java.sql.Date;
import java.util.Map;


public class Order extends Entity{

    private long user_id;
    private Date createDate;
    private Status status;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public Date getCreateDate() {
        return createDate;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Map<Product, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Product, Integer> map) {
        this.map = map;
    }

    private Map<Product, Integer> map;


    public enum Status {
        REGISTERED, CONFIRMED, CANCELED, DONE;
    }

}
