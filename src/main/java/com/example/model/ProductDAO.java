package com.example.model;

import com.example.Constants;
import com.example.model.entity.Category;
import com.example.model.entity.Employee;
import com.example.model.entity.Product;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ProductDAO {

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

    private static final String SQL_FIND_PRODUCT_BY_ID = "SELECT * FROM product LEFT JOIN category ON category_id=category.id WHERE product.id=?";
    private static final String SQL_GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE id=?";
    public static final String SQL_GET_BRANDS_BY_CATEGORY = "SELECT brand FROM product WHERE category_id = ? GROUP BY brand";
    public static final String SQL_INSERT_PRODUCT = "INSERT INTO product(name,brand,description,price,image_url,category_id,country)VALUES(?,?,?,?,?,?,?)";
    public static final String SQL_UPDATE_PRODUCT = "UPDATE product SET name=?,brand=?,description=?,price=?,image_url=?,category_id=?,country=? WHERE id=?";
    public static final String SQL_GET_POPULAR_PRODUCTS = "SELECT *, COUNT(*) as count FROM order_content JOIN product ON product_id=product.id GROUP BY product_id ORDER BY count DESC LIMIT ?";
    public static final String SQL_GET_ALL_CATEGORIES = "SELECT * FROM category";


    List<Product> productList = ProductList.getInstance();

    public List<Product> getAllProducts() {
        return productList;
    }
    public List<Product> searchProductByName(String name) {
        Comparator<Product> groupByComparator = Comparator.comparing(Product::getName)
                .thenComparing(Product::getBrand);
        List<Product> result = productList
                .stream()
                .filter(e -> e.getName().equalsIgnoreCase(name) || e.getBrand().equalsIgnoreCase(name))
                .sorted(groupByComparator)
                .collect(Collectors.toList());
        return result;
    }
    public static List<Product> getPopularProducts(long limit) {
        List<Product> products = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_POPULAR_PRODUCTS)) {
            pst.setLong(1, limit);
            try(ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    products.add(mapProduct(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }
    public static List<Product> getAllProductsByCategory(Category category, String sort, int priceFrom, int priceTo, List<String> brandList, int offset, int limit) {
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
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(
                     "SELECT * FROM product WHERE " + priceCause +
                             brandCause + " category_id=? ORDER BY " +
                     COLUMN_TO_SORT_MAP.get(sort) + " " + SORT_DIRECTION_MAP.get(sort) + " LIMIT ?, ?")) {
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
    public static Product findProductById(long id) {
        Product product = null;
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_FIND_PRODUCT_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    product = mapProductAndCategory(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }
    private static Product mapProductAndCategory(ResultSet rs) throws SQLException {
        Product product = mapProduct(rs);
        Category category = mapCategory(rs);
        product.setCategory(category);
        return product;
    }

    public long addProduct(Product product) {
        long id = 0;

        try (Connection con = ConnectionPool.getInstance().getConnection()) {
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
        try (Connection con = ConnectionPool.getInstance().getConnection()) {
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
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(product.getId() > 0 ? SQL_UPDATE_PRODUCT : SQL_INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
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
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productId;
    }

    public boolean deleteProduct(long id) {
        Predicate<Product> product = e -> e.getId() == id;
        if (productList.removeIf(product)) {
            return true;
        } else {
            return false;
        }
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
        try (Connection con = ConnectionPool.getInstance().getConnection();
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

    public static List<String> getBrandsByCategory(long categoryId) {
        List<String> brands = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_BRANDS_BY_CATEGORY)) {
            pst.setLong(1, categoryId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    brands.add(rs.getString(1));
                }
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