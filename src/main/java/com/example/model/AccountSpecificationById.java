package com.example.model;

import com.example.model.bean.Account;

public class AccountSpecificationById extends AccountSpecification {
    private Long id;

    public AccountSpecificationById(Long id) {
        this.id = id;
    }

    @Override
    public String toSqlClauses() {
        String childCondition = String.format("WHERE id=%s", id);
        String result = super.toSqlClauses() + " " + childCondition;
        return result;
    }

    @Override
    public boolean specified(Account account) {
        return super.
                specified(account) && account.getId() == (id);
    }
}

