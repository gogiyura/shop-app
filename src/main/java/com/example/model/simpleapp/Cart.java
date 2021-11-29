package com.example.model.simpleapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Integer id;
    private Map<Integer, Integer> map;

    public void add(int product_id, int count) {
        if(map == null) {
            map = new HashMap<>();
        }
        if (map.containsKey(product_id)) {
            map.replace(product_id, count);
        } else {
            map.put(product_id, count);
        }
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }
}
