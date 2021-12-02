package com.example.model;

import com.example.model.bean.Category;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryController extends AbstractController implements Repository<Category> {
    private static CategoryController instance;

    public static CategoryController getInstance() {
        if (instance == null) {
            instance = new CategoryController();
        }
        return instance;
    }

    public static final String TABLE = "category";
    public static final String FIELDS = "(name)";
    public static final String EMPTY_FIELDS = "(?)";
    public static final String UPD_FIELDS = "name=?";

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
    public long add(Category category) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(RequestSQL.makeQuery(RequestSQL.INSERT, TABLE, FIELDS, EMPTY_FIELDS));
        ps.setString(1, category.getName());
        int affectedRows = ps.executeUpdate();
        if (affectedRows > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                category.setId(rs.getLong(1));
            }
            rs.close();
        }
        closePreparedStatement(ps);
        c.close();
        return category.getId();
    }

    @Override
    public boolean remove(Long id) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(RequestSQL.makeQuery(RequestSQL.DELETE, TABLE, "id=?", "id"));
        ps.setLong(1, id );
        boolean result = ps.execute();
        closePreparedStatement(ps);
        c.close();
        return result;
    }

    @Override
    public boolean update(Category category) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(RequestSQL.makeQuery(RequestSQL.UPDATE, TABLE, "id=?", UPD_FIELDS));
        int k=1;
        ps.setString(k++, category.getName());
        ps.setLong(k++, category.getId());
        int affectedRows = ps.executeUpdate();
        boolean result = affectedRows > 0;
        closePreparedStatement(ps);
        c.close();
        return result;
    }

    @Override
    public List<Category> query(SqlSpecification specification) {
        return null;
    }

}
