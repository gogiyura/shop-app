package com.example.model;

import com.example.model.bean.Order;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderController extends AbstractController implements Repository<Order> {
    private static OrderController instance;

    public static OrderController getInstance() {
        if (instance == null) {
            instance = new OrderController();
        }
        return instance;
    }

    public static final String TABLE = "order";
    public static final String FIELDS = "(user_id, status)";
    public static final String EMPTY_FIELDS = "(?, ?)";
    public static final String UPD_FIELDS = "user_id=?, status=?";

    public long length() throws SQLException {
        long result = 0;
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(RequestSQL.count(TABLE));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int k=1;
            result=rs.getLong(k++);
        }
        closePreparedStatement(ps);
        c.close();
        return result;
    }

    @Override
    public long add(Order order) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(RequestSQL.
                makeQuery(RequestSQL.INSERT, TABLE, FIELDS, EMPTY_FIELDS));
        int k=1;
        ps.setLong(k++, order.getUserId());
        ps.setString(k++, order.getStatus().toString());
        int affectedRows = ps.executeUpdate();
        if(affectedRows > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                k=1;
                order.setId(rs.getLong(k++));
                order.setCreateDate(rs.getTimestamp(k++));
            }
            rs.close();
        }
        closePreparedStatement(ps);
        c.close();
        return order.getId();
    }

    @Override
    public boolean remove(Long id) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(RequestSQL.makeQuery(
                RequestSQL.DELETE, TABLE, "id=?", "id"));
        ps.setLong(1, id );
        boolean result = ps.execute();
        closePreparedStatement(ps);
        c.close();
        return result;
    }

    @Override
    public boolean update(Order order) throws SQLException {

        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(RequestSQL.
                makeQuery(RequestSQL.UPDATE, TABLE, "id=?", UPD_FIELDS));
        int k=1;
        ps.setLong(k++, order.getUserId());
        ps.setString(k++, order.getStatus().toString());
        ps.setLong(k++, order.getId());
        int affectedRows = ps.executeUpdate();
        boolean result = affectedRows > 0;
        closePreparedStatement(ps);
        c.close();
        return result;
    }

    @Override
    public List<Order> query(SqlSpecification Specification) {
        return null;
    }
}
