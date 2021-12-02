package com.example.model;

import com.example.model.bean.Account;
import com.example.model.bean.Product;
import com.example.model.bean.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductController extends AbstractController implements Repository<Product> {
    private static ProductController instance;

    public static ProductController getInstance() {
        if (instance == null) {
            instance = new ProductController();
        }
        return instance;
    }

    public static final String TABLE = "product";
    public static final String FIELDS = "(name, description, category_id, price, img)"; //for insert row
    public static final String EMPTY_FIELDS = "(?, ?, ? ,?, ?)"; //for insert row
    public static final String UPD_FIELDS = "name=?, description=?, category_id=?, price=?, img=?"; // for update

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
    public long add(Product product) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(
                RequestSQL.makeQuery(RequestSQL.INSERT, TABLE, FIELDS, EMPTY_FIELDS));
        int k=1;
        ps.setString(k++, product.getName());
        ps.setString(k++, product.getDescription());
        ps.setLong(k++, product.getCategory_id());
        ps.setFloat(k++, product.getPrice());
        ps.setString(k++, product.getImg());
        int affectedRows = ps.executeUpdate();
        if(affectedRows > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                product.setId(rs.getLong(1));
                product.setCreateDate(rs.getTimestamp(2));
            }
            rs.close();
        }
        closePreparedStatement(ps);
        c.close();
        return product.getId();
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
    public boolean update(Product product) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(
                RequestSQL.makeQuery(RequestSQL.UPDATE, TABLE, "id=?", UPD_FIELDS));
        int k=1;
        ps.setString(k++, product.getName());
        ps.setString(k++, product.getDescription());
        ps.setLong(k++, product.getCategory_id());
        ps.setFloat(k++, product.getPrice());
        ps.setString(k++, product.getImg());
        ps.setLong(k++, product.getId());
        int affectedRows = ps.executeUpdate();
        boolean result = affectedRows > 0;
        closePreparedStatement(ps);
        c.close();
        return result;
    }

    @Override
    public List<Product> query(SqlSpecification specification) throws SQLException {
        String query = specification.toSqlClauses();
        List<Product> result = new LinkedList();
        Product product;
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            product = new Product();
            int k = 1;
            product.setId(rs.getLong(k++));
            product.setName(rs.getString(k++));
            product.setDescription(rs.getString(k++));
            product.setCategory_id(rs.getInt(k++));
            product.setPrice(rs.getFloat(k++));
            product.setImg(rs.getString(k++));
            product.setCreateDate(rs.getDate(k++));
            result.add(product);
        }
        closePreparedStatement(ps);
        c.close();
        return result;
    }
}
