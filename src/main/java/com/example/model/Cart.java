package com.example.model;

import com.example.model.entity.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Cart {
    private Map<Product, Integer> cart;

    public Cart(Product product, Integer i) {
        this.cart = new HashMap<Product, Integer>(){};
        this.cart.put(product, i);
    }

    public Cart(Product product) {
        new Cart(product, 1);
    }

    public Map<Product, Integer> getCart() {

        return cart;
    }

    public void add(Product product) {
        add(product, 1);
    }

    public void add(Product product, int q) {
        if(cart.containsKey(product)) {
            cart.replace(product, cart.get(product) + q);
        } else {
            cart.put(product, q);
        }
    }

    public boolean removeItem(Product product) {
        boolean res = false;
        if (cart.containsKey(product)) {
            cart.remove(product);
            res = true;
        }
        return res;
    }

    public void setQuantityItem(Product product, int q) {
        if(cart.containsKey(product)) {
            cart.replace(product, q);
        } else {
            cart.put(product, q);
        }
    }

    public Integer getPrice() {
        Integer price = 0;
        return price;
    }
}
