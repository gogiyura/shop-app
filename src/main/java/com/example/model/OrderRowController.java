package com.example.model;

import com.example.model.bean.OrderRow;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderRowController extends AbstractController implements Repository<OrderRow> {
    private static OrderRowController instance;

    public static OrderRowController getInstance() {
        if (instance == null) {
            instance = new OrderRowController();
        }
        return instance;
    }

    public static final String TABLE = "order_row";
    public static final String FIELDS = "(order_id, product_id, count)";
    public static final String EMPTY_FIELDS = "(?, ?, ?)";
    public static final String UPD_FIELDS = "order_id=?, product_id=?, count=?";

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
    public long add(OrderRow orderRow) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(RequestSQL.
                makeQuery(RequestSQL.INSERT, TABLE, FIELDS, EMPTY_FIELDS));
        int k=1;
        ps.setLong(k++, orderRow.getOrderId());
        ps.setLong(k++, orderRow.getProductId());
        ps.setLong(k++, orderRow.getCount());
        int affectedRows = ps.executeUpdate();
        if(affectedRows > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderRow.setId(rs.getLong(1));
            }
            rs.close();
        }
        closePreparedStatement(ps);
        c.close();

        return orderRow.getId();
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
    public boolean update(OrderRow orderRow) throws SQLException {

        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(RequestSQL.
                makeQuery(RequestSQL.UPDATE, TABLE, "id=?", UPD_FIELDS));
        int k=1;
        ps.setLong(k++, orderRow.getOrderId());
        ps.setLong(k++, orderRow.getProductId());
        ps.setLong(k++, orderRow.getCount());
        ps.setLong(k++, orderRow.getId());
        int affectedRows = ps.executeUpdate();
        boolean result = affectedRows > 0;
        closePreparedStatement(ps);
        c.close();
        return result;
    }

    @Override
    public List<OrderRow> query(SqlSpecification specification) {
        return null;
    }
}
