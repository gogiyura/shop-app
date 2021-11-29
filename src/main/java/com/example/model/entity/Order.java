package com.example.model.entity;

import com.example.model.ProductDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;


public class Order extends Entity{

    private long id;
    private long user_id;
    private Date createDate;
    private Status status;
    private Map<Integer, Integer> map;

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

    public Map<Integer, Integer> getMap() {

        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }

    public Order(long user_id, Cart cart) {
        this.map = cart.getMap();
        this.user_id = user_id;
        this.createDate = new Date();
        this.status = Status.REGISTERED;
    }

    public int getPrice() {
        int sum=0;
        for(int id : map.keySet()){
            try {
                sum += ProductDAO.findProductById(id).getPrice()*map.get(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    public enum Status {
        REGISTERED, CONFIRMED, CANCELED, DONE;
    }
}
