package com.example.model.entity;

import java.sql.Date;

public class Product extends Entity{

    private String name;
    private String brand;
    private String description;
    private int price;
    private String imageUrl;
    private Category category;
    private String country;
    private Date createTime;

    public long getProductCategoryId() {
        Category cat = this.getCategory();
        return cat.getId();
    }

    public Product(String name, String brand, String description, String price, String category, String country) {

        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = Integer.parseInt(price);
        this.category = new Category();
        this.category.setName(category);
        this.country = country;
    }

    public Product(String name, String brand, String description, String price, String category, String country, Long id) {
        Product product = new Product(name, brand, description, price, category, country);
        product.setId(id);
    }

    public Product(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
