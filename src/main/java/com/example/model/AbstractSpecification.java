package com.example.model;

public abstract class AbstractSpecification<E> implements Specification<E>, SqlSpecification{
    @Override
    public boolean specified(E e) {
        return false;
    }

    @Override
    public String toSqlClauses() {
        return "";
    }

}
