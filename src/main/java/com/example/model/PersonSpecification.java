package com.example.model;

import com.example.model.bean.Person;

public class PersonSpecification extends AbstractSpecification<Person>{
    private Long accountId;
    @Override
    public boolean specified(Person person) {
        if(person==null){
            return false;
        }
        return true;
    }

    @Override
    public String toSqlClauses() {
        String result = String.format("SELECT * FROM person WHERE accound_id=%s", accountId);
        return result;
    }
}
