package com.example.model.bean;

import java.io.Serializable;
import java.util.Date;


public class Order implements Serializable {

    private long id;
    private long userId;
    private Status status;
    private Date createDate;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public enum Status {REGISTERED, CONFIRMED, CANCELED, DONE;}

    public Order() {}
    public Order(long id, long user_id, Date createDate, Status status) {
        this.id = id;
        this.userId = user_id;
        this.createDate = createDate;
        this.status = status;
    }
}
