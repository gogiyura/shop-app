package com.example.service;

import com.example.model.ProductController;
import com.example.model.ProductSpecification;
import com.example.model.ProductSpecificationWhere;
import com.example.model.bean.Product;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductService implements Service<Product>{
    private static final Logger log = Logger.getLogger(ProductService.class);

    private static ProductController instance = ProductController.getInstance();


    public List getAll() {
        List<Product> result = new LinkedList();
        try {
            result = instance.query(new ProductSpecification());
        } catch (SQLException e) {
            log.trace("Не удалось выполнить запрос", e);
        }
        log.info("Запрос выполнен. Получено " + result.size() + " записей из таблицы product.");
        return result;
    }

    @Override
    public void add(Product product) {
        long id=0;
        try {
            id = instance.add(product);
        } catch (SQLException e) {
            log.trace("Не удалось добавть сущность в базу", e);
        }
        if(id>0) {
            log.info("В базу добавлена сущность" + product.toString());
        } else {
            log.info("В базу не добавлена сущность" + product.toString()+ "\nЧто-то пошло не так");
        }
    }

    @Override
    public void delete(Long id) {
        boolean flag = false;
        try {
            flag = instance.remove(id);
        } catch (SQLException e) {
            log.trace("Не удалось удалить сущность", e);
        } if(flag) {
            log.info("Была удалена сущность #" + id);
        } else {
            log.info("Проблемы с удалением сущности #" + id+ "\nЧто-то пошло не так");
        }

    }

    @Override
    public void edit(Product product) {
        boolean flag = false;
        try {
            flag = instance.update(product);
        } catch (SQLException e) {
            log.trace("Транзакция не удалась", e);
        } if(flag) {
            log.trace("Транзакция успешно проведена");
        } else {
            log.trace("В ходе проведения транзакции возникали ошибки.");
        }
        //TODO придумать ещё сообщения(?)
    }

    @Override
    public Product find(Long id) {
        Product response = null;
        try {
            instance.query(new ProductSpecificationWhere());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;}

    @Override
    public Product find(String name) {return null;}

}
