package com.example.model;

import com.example.model.entity.Product;

import java.util.Map;

public class Cart {
    private Map<Product, Integer> cart;

    public Map<Product, Integer> getCart() {

        return cart;
    }

    public void addItem(Product product) {
        addItem(product, 1);
    }

    public void addItem(Product product, int q) {
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
}
