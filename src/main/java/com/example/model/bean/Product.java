package com.example.model.bean;

import java.util.Date;

public class Product {

    private long id, category_id;
    private String name, description, img;
    private float price;
    private Date createDate;

    public Product() {}
    public Product(long id, long category_id, String name, String description,
                   float price, String img, Date createDate) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.createDate = createDate;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getCreateDate() {return createDate;}
    public void setCreateDate(Date createDate) {this.createDate = createDate;}
    public long getCategory_id() {return category_id;}
    public void setCategory_id(long category_id) {this.category_id = category_id;}
    public String getImg() {return img;}
    public void setImg(String img) {this.img = img;}

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                '}';
    }
}