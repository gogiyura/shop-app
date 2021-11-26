package com.example.model;

import com.example.model.entity.Employee;
import com.example.model.entity.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductDAO {

    List<Product> productList = ProductList.getInstance();

    public List<Product> getAllProducts() {
        return productList;
    }

    public List<Product> searchProductByName(String name) {
        Comparator<Product> groupByComparator = Comparator.comparing(Product::getName)
                .thenComparing(Product::getBrand);
        List<Product> result = productList
                .stream()
                .filter(e -> e.getName().equalsIgnoreCase(name) || e.getBrand().equalsIgnoreCase(name))
                .sorted(groupByComparator)
                .collect(Collectors.toList());
        return result;
    }

    public Product getProduct(long id) throws Exception {
        Optional<Product> match
                = productList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        if (match.isPresent()) {
            return match.get();
        } else {
            throw new Exception("The Product id " + id + " not found");
        }
    }

    public long addProduct(Product product) {
        productList.add(product);
        return product.getId();
    }

    public boolean updateProduct(Product customer) {
        int matchIdx = 0;
        Optional<Product> match = productList.stream()
                .filter(c -> c.getId() == customer.getId())
                .findFirst();
        if (match.isPresent()) {
            matchIdx = productList.indexOf(match.get());
            productList.set(matchIdx, customer);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteProduct(long id) {
        Predicate<Product> product = e -> e.getId() == id;
        if (productList.removeIf(product)) {
            return true;
        } else {
            return false;
        }
    }
}