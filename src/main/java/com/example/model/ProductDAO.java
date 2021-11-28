package com.example.model;

import com.example.model.Utils.*;
import com.example.model.entity.Category;
import com.example.model.entity.Product;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.function.Predicate;


public class ProductDAO {
    private static final Logger log = Logger.getLogger(ProductDAO.class);

    private static final String TABLE_CATEGORY = "category";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_BRAND = "brand";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_PRICE = "price";
    private static final String FIELD_IMAGE_URL = "imgUrl";
    private static final String FIELD_COUNTRY = "country";
    private static final String FIELD_CREATE_TIME = "create_time";

    private static final Map<String, String> COLUMN_TO_SORT_MAP = new HashMap();
    private static final Map<String, String> SORT_DIRECTION_MAP = new HashMap();
    private static final String SQL_GET_ALL_CATEGORIES = "SELECT * FROM category limit 5";
    private static final String SQL_GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE id=? LIMIT 5";
    private static final String SQL_GET_ALL_PRODUCTS = "SELECT * FROM product limit 5";
    private static final String SQL_GET_POPULAR_PRODUCTS = "SELECT * FROM product where name=? limit 5";
    private static final String SQL_GET_PRODUCTS_BY_CATEGORY = "SELECT * FROM product p where p.category_id=? LIMIT 5";
    private static final String SQL_FIND_PRODUCT_BY_ID = "SELECT * FROM product LEFT JOIN category ON category_id=category.id WHERE product.id=? LIMIT 5";
    private static final String SQL_SEARCH_PRODUCT_BY_NAME = "SELECT * FROM product WHERE product.name=? LIMIT 5";

    public static final String SQL_INSERT_PRODUCT = "INSERT INTO product(name,brand,description,price,image_url,category_id,country)VALUES(?,?,?,?,?,?,?)";
    public static final String SQL_UPDATE_PRODUCT = "UPDATE product SET name=?,brand=?,description=?,price=?,image_url=?,category_id=?,country=? WHERE id=?";
    public static final String SQL_GET_BRANDS_BY_CATEGORY = "SELECT brand FROM product WHERE category_id = ? GROUP BY brand LIMIT 5";
    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE id=?";

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try (Connection con = connectionPool.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_PRODUCTS);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                productList.add(mapProduct(rs));
            }
        } catch (SQLException ex) {
            log.trace("В базе не нашлось продуктов");
        } catch (NullPointerException e) {
            log.trace( "NPE");
        }
        return productList;
    }


    public static List<Product> searchProductByName(String name) throws SQLException{
        List<Product> productList = new ArrayList();
        try (Connection con = connectionPool.getConnection())
        {
            PreparedStatement pst = null;
                pst = con.prepareStatement(SQL_SEARCH_PRODUCT_BY_NAME);
                pst.setString(1,name);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    productList.add(mapProduct(rs));
                }
                Utils.closeRSet(rs);
        Utils.closePst(pst);
        }
        return productList;
    }

    public static List<Product> getPopularProducts(long limit) throws SQLException{
        List<Product> products = new ArrayList<>();
        try (Connection con = connectionPool.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_POPULAR_PRODUCTS)) {
            pst.setLong(1, limit);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                    products.add(mapProduct(rs));
                }
            Utils.closeRSet(rs);
            Utils.closePst(pst);
            }
        return products;
    }

    public static List<Product> getAllProductsByCategory (
            Category category, String sort, int priceFrom,
            int priceTo, List<String> brandList, int offset, int limit)
            throws SQLException {
        List<Product> list = new ArrayList<>(limit);
        String priceCause= "";

/*        if(priceFrom != null) {
            priceCause += " price >= " + priceFrom + " AND ";
            if(null != priceTo)
                priceCause += " price <= " + priceTo + " AND ";
        } else {
            if(null != priceTo)
                priceCause += " price <= " + priceTo + " AND ";
        }*/

        StringBuilder brandCause = new StringBuilder();
        if(!brandList.isEmpty()) {
            brandCause.append(" brand IN('").append(brandList.get(0)).append("'");
        }

        for (int i = 1; i < brandList.size(); i++) {
            brandCause.append(",'").append(brandList.get(i)).append("'");
        }

        if(!brandList.isEmpty()) {
            brandCause.append(") AND ");
        }


        try (Connection con = connectionPool.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_PRODUCTS_BY_CATEGORY);
             ) {
            pst.setLong(1, category.getId());
            pst.setInt(2, offset);
            pst.setInt(3, limit);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Product product = mapProduct(rs);
                    product.setCategory(category);
                    list.add(product);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static Product findProductById(long id) throws SQLException{
        Product product = null;
        try (Connection con = connectionPool.getConnection()) {
            PreparedStatement pst = con.prepareStatement(SQL_FIND_PRODUCT_BY_ID);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                product = mapProductAndCategory(rs);
            }
            Utils.closeRSet(rs);
            Utils.closePst(pst);
        }
        return product;
    }


    private static Product mapProductAndCategory(ResultSet rs) throws SQLException {
        Product product = mapProduct(rs);
        Category category = mapCategory(rs);
        product.setCategory(category);
        return product;
    }


    public static long addProduct(Product product) {
        long id = 0;

        try (Connection con = connectionPool.getConnection()) {
            try {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);
                //name,brand,description,price,image_url,category_id,country
                ps.setString(1, product.getName());
                ps.setString(2, product.getBrand());
                ps.setString(3, product.getDescription());
                ps.setInt(4, product.getPrice());
                ps.setString(5, product.getImageUrl());
                ps.setLong(6, product.getProductCategoryId());
                int affectedRows = ps.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            id = rs.getLong(1);
                        }
                    }
                }
                Utils.closePst(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

/*    public boolean updateProduct(Product customer) {
        int matchIdx = 0;
        Optional<Product> match = productList.stream()
                .filter(c -> c.getId() == customer.getId())
                .findFirst();
        if (match.isPresent()) {
            matchIdx = productList.indexOf(match.get());
            productList.set(matchIdx, customer);
            return true;
        } else {
            return false;
        }
    }*/

    public static boolean updateProduct(Product product) {
        boolean res = false;
        try (Connection con = connectionPool.getConnection()) {
            try {
                PreparedStatement ps = con.prepareStatement(SQL_UPDATE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
                //name,brand,description,price,image_url,category_id,country
                ps.setString(1, product.getName());
                ps.setString(2, product.getBrand());
                ps.setString(3, product.getDescription());
                ps.setInt(4, product.getPrice());
                ps.setString(5, product.getImageUrl());
                ps.setLong(6, product.getProductCategoryId());
                ps.setLong(7, product.getId());
                int affectedRows = ps.executeUpdate();
                res = affectedRows > 0;

                Utils.closePst(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public static long addOrUpdateProduct(Product product) {
        long productId = 0;

        // Since insert and update SQL queries are similar, we preparing statement in one line
        try (Connection con = connectionPool.getConnection();
             PreparedStatement pst = con.prepareStatement(
                     product.getId() > 0 ? SQL_UPDATE_PRODUCT : SQL_INSERT_PRODUCT,
                     Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, product.getName());
            pst.setString(2, product.getBrand());
            pst.setString(3, product.getDescription());
            pst.setTimestamp(4, new Timestamp(product.getCreateTime().getTime()));
            pst.setInt(5, product.getPrice());
            pst.setString(6, product.getImageUrl());
            pst.setLong(7, product.getCategory().getId());
            pst.setString(8, product.getCountry());

            if (product.getId() > 0) {
                pst.setLong(9, product.getId());
                productId = product.getId();
            }
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                try(ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        productId = generatedKeys.getLong(1);
                    }
                    Utils.closeRSet(generatedKeys);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productId;
    }

    public static boolean deleteProduct(long id) throws SQLException {
        boolean res = false;
        Predicate<Product> product = e -> e.getId() == id;
        Connection con = connectionPool.getConnection();
        PreparedStatement ps = con.prepareStatement(DELETE_PRODUCT_BY_ID);
        try {
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            res = rs.getBoolean(1);
            Utils.closeRSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Utils.closePst(ps);
        Utils.closeCon(con);
        return res;
    }

    private static Product mapProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong(FIELD_ID));
        product.setName(rs.getString(FIELD_NAME));
        product.setBrand(rs.getString(FIELD_BRAND));
        product.setDescription(rs.getString(FIELD_DESCRIPTION));
        product.setCreateTime(new Date(rs.getTimestamp(FIELD_CREATE_TIME).getTime()));
        product.setPrice(rs.getInt(FIELD_PRICE));
        product.setImageUrl(rs.getString(FIELD_IMAGE_URL));
        product.setCountry(rs.getString(FIELD_COUNTRY));
        return product;
    }

    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Connection con = connectionPool.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_CATEGORIES);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                categories.add(mapCategory(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categories;
    }

    public static Category findCategoryById(long id) {
        Category category = null;
        try (Connection con = connectionPool.getConnection();
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
        if(category!=null){
            return category;
        } else { throw new NullPointerException();
    }}

    public static List<String> getBrandsByCategory(long categoryId) {
        List<String> brands = new ArrayList<>();
        try (Connection con = connectionPool.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_BRANDS_BY_CATEGORY)) {
            pst.setLong(1, categoryId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    brands.add(rs.getString(1));
                }
                Utils.closeRSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return brands;
    }

    private static Category mapCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong(TABLE_CATEGORY + "." + FIELD_ID));
        category.setName(rs.getString(TABLE_CATEGORY + "." + FIELD_NAME));
        return category;
    }

}