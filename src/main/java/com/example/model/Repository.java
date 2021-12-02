package com.example.model;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface Repository<E> {
    long add(E e) throws SQLException, NamingException;
    boolean remove(Long id)  throws SQLException, NamingException ;
    boolean update(E e) throws SQLException, NamingException; // Think it as replace for set
    List<E> query(SqlSpecification specification) throws SQLException, NamingException;

}
