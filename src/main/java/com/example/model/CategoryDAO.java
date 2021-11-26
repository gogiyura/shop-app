package com.example.model;

import com.example.model.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO {
    private static final String SQL_GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE id=?";
    private static final String TABLE_CATEGORY = "category";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";


    public static Category findCategoryById(long id) {
        Category category = null;
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_CATEGORY_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    category = mapCategory(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return category;
    }

    private static Category mapCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong(TABLE_CATEGORY + "." + FIELD_ID));
        category.setName(rs.getString(TABLE_CATEGORY + "." + FIELD_NAME));
        return category;
    }
}
