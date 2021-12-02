package com.example.model;

import com.example.model.bean.Category;

public class CategorySpecification extends AbstractSpecification<Category> {
    @Override
    public String toSqlClauses() {
        String result = "SELECT * FROM category";
        return result;
    }

    @Override
    public boolean specified(Category category) {
        return category != null;
    }
}
