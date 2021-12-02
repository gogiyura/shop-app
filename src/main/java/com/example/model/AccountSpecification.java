package com.example.model;

import com.example.model.bean.Account;

public class AccountSpecification extends AbstractSpecification<Account>{

    @Override
    public boolean specified(Account account) {
        if(account != null){
            return true;
        } else return false;
    }

    @Override
    public String toSqlClauses() {
        return "SELECT * FROM account";
    }
}
