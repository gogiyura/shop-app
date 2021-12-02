package com.example.references.logination.trash;

import com.example.references.entity.Entity;

import java.sql.Date;

public class Product extends Entity {

    private String name, brand, description, imageUrl, country;
    private Integer price;
    private Long categoryId;
    private Date createTime;

    public static String getTableHeaderRow () {
        return "<td>name</td>" +
                "<td>price</td>" +
                "<td>brand</td>" +
                "<td>description</td>" +
                "<td>country</td>";
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", country='" + country + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", createTime=" + createTime +
                '}';
    }

    public Product(String name, String brand, String description, String price, long category, String country) {

        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = Integer.parseInt(price);
        this.categoryId = category;
        this.country = country;
    }

    public Product(String name, String brand, String description, String price, long category, String country, Long id) {

        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = Integer.parseInt(price);
        this.categoryId = category;
        this.country = country;
        this.setId(id);
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
