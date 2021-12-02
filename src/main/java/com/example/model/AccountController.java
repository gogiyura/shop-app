package com.example.model;

import com.example.model.bean.Account;
import com.example.model.bean.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AccountController extends AbstractController implements Repository<Account> {
    private static AccountController instance;
    public static AccountController getInstance() {
        if (instance == null) {
            instance = new AccountController();
        }
        return instance;
    }

    private static final String UPD = "UPDATE account SET  login=?, password=?, role=?, blocked=? WHERE id=?";
    private static final String ADD = "INSERT INTO account (login, password, role, blocked) VALUE(?, ?, ?, ?)";
    private static final String REMOVE = "DELETE FROM account WHERE id=?";
    public static final String COUNT = "SELECT count(*) FROM account";
    public static final String TABLE = "account";

    public long length() throws SQLException {
        long result = 0;
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(COUNT);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result=rs.getLong(1);
                }
        closePreparedStatement(ps);
        c.close();
        return result;
    }

    @Override
    public long add(Account account) throws SQLException {
        account.setBlocked(false);
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(ADD);
        int k=1;
        ps.setString(k++, account.getLogin());
        ps.setString(k++, account.getPassword());
        ps.setString(k++, account.getRole().toString());
        ps.setBoolean(k++, account.getBlocked());
        int affectedRows = ps.executeUpdate();
        if(affectedRows > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                account.setId(rs.getLong(1));
                account.setCreateDate(rs.getTimestamp(2));
            }
            rs.close();
        }
        closePreparedStatement(ps);
        c.close();
        return account.getId();
    }

    @Override
    public boolean remove(Long id) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(REMOVE);
        ps.setLong(1, id );
        boolean result = ps.execute();
        closePreparedStatement(ps);
        c.close();
        return result;
    }

    @Override
    public boolean update(Account account) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(UPD);
        int k=1;
        ps.setString(k++, account.getLogin());
        ps.setString(k++, account.getPassword());
        ps.setString(k++, account.getRole().toString());
        ps.setBoolean(k++, account.getBlocked());
        ps.setLong(k++, account.getId());
        int affectedRows = ps.executeUpdate();
        boolean result = affectedRows > 0;
        closePreparedStatement(ps);
        c.close();
        return result;
    }

    @Override
    public List<Account> query(SqlSpecification specification) throws SQLException {
        String query = specification.toSqlClauses();
        List<Account> result = new LinkedList();
        Account account;
        Connection c = getConnection();
        PreparedStatement ps = getPreparedStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            account = new Account();
            int k = 1;
            account.setId(rs.getLong(k++));
            account.setLogin(rs.getString(k++));
            account.setPassword(rs.getString(k++));
            account.setRole(Role.valueOf(rs.getString(k++)));
            account.setBlocked(rs.getBoolean(k++));
            account.setCreateDate(rs.getTimestamp(k++));
            result.add(account);
        }
        closePreparedStatement(ps);
        c.close();
        return result;
    }
}
