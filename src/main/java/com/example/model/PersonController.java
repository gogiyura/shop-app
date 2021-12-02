package com.example.model;

import com.example.model.bean.Person;
import com.example.model.bean.Product;

import javax.naming.NamingException;
import java.sql.*;
import java.util.List;

//id, firstName, lastName, phone, email, birthday

public class PersonController extends AbstractController implements Repository<Person> {
    private static PersonController instance;

    public static PersonController getInstance() {
        if (instance == null) {
            instance = new PersonController();
        }
        return instance;
    }

    public static final String TABLE = "person";
    public static final String FIELDS = "(id, first_name, last_name, phone, email, birthday)"; //for insert row
    public static final String EMPTY_FIELDS = "(?, ?, ?, ?, ?, ?)"; //for insert row
    public static final String UPD_FIELDS = "id=?, first_name=?, last_name=?, phone=?, email=?, birthday=?"; // for update

    public long length() throws SQLException {
        long result = 0;
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(RequestSQL.count(TABLE));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result=rs.getLong(1);
        }
        closePreparedStatement(ps);
        c.close();
        return result;
    }

    @Override
    public long add(Person person) throws SQLException{
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(RequestSQL.
                makeQuery(RequestSQL.INSERT, TABLE, FIELDS, EMPTY_FIELDS));
        int k=1;
        ps.setLong(k++, person.getId());
        ps.setString(k++, person.getFirstName());
        ps.setString(k++, person.getLastName());
        ps.setString(k++, person.getPhone());
        ps.setString(k++, person.getEmail());
        ps.setDate(k++, (Date) person.getBirthday());
        int affectedRows = ps.executeUpdate();
        if(affectedRows > 0) {
        }
        closePreparedStatement(ps);
        c.close();
        return person.getId();
    }

    @Override
    public boolean remove(Long id) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(RequestSQL.
                makeQuery(RequestSQL.DELETE, TABLE, "id=?", "id"));
        ps.setLong(1, id );
        boolean result = ps.execute();
        closePreparedStatement(ps);
        c.close();
        return result;
    }

    @Override
    public boolean update(Person person) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(RequestSQL.
                makeQuery(RequestSQL.UPDATE, TABLE, "id=?", UPD_FIELDS));
        int k=1;
        ps.setString(k++, person.getFirstName());
        ps.setString(k++, person.getLastName());
        ps.setString(k++, person.getPhone());
        ps.setString(k++, person.getEmail());
        ps.setDate(k++, (Date) person.getBirthday());
        ps.setLong(k++, person.getId());
        int affectedRows = ps.executeUpdate();
        boolean result = affectedRows > 0;
        closePreparedStatement(ps);
        c.close();
        return result;
    }

    @Override
    public List<Person> query(SqlSpecification specification) {
        return null;
    }
}

