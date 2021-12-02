package com.example.model;

import com.example.model.bean.Account;

public class AccountSpecificationByLogin extends AccountSpecification{

    private String login;

    public AccountSpecificationByLogin(String login){
        this.login = login;
    }

    @Override
    public String toSqlClauses() {
        String childCondition = String.format("WHERE login=%s", login);
        String result = super.toSqlClauses() + " " + childCondition;
        return result;
    }

    @Override
    public boolean specified(Account account) {

        return super.specified(account) && account.getLogin().equals(login);
    }
}
