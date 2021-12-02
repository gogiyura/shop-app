package com.example.service;

import java.util.List;

public interface Service<T> {
    List<T> getAll();
    void add(T t);
    void delete(Long id);
    void edit(T t);
    T find(String name);
    T find(Long id);

}
