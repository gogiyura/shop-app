package com.example.model;

import com.example.model.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(
        name = "ProductServlet",
        urlPatterns = {"/catalog"})
public class ProductServlet extends HttpServlet {

    ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("searchAction");
        if (action!=null){
            switch (action) {
                case "searchById":
                    searchProductById(req, resp);
                    break;
                case "searchByName":
                    searchProductByName(req, resp);
                    break;
            }
        }else{
            List<Product> result = productDAO.getAllProducts();
            forwardListProducts(req, resp, result);
        }
    }

    private void searchProductById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long idProduct = Integer.valueOf(req.getParameter("idProduct"));
        Product product = null;
        try {
            product = productDAO.getProduct(idProduct);
        } catch (Exception ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("product", product);
        req.setAttribute("action", "edit");
        String nextJSP = "/jsp/new-product.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void searchProductByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String productName = req.getParameter("productName");
        List<Product> result = productDAO.searchProductByName(productName);
        forwardListProducts(req, resp, result);
    }

    private void forwardListProducts(HttpServletRequest req, HttpServletResponse resp, List productList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/list-products.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("productList", productList);
        dispatcher.forward(req, resp);
    }





    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addProductAction(req, resp);
                break;
            case "edit":
                editProductAction(req, resp);
                break;
            case "remove":
                removeProductByName(req, resp);
                break;
        }
    }


    private void addProductAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String brand = req.getParameter("brand");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        String category = req.getParameter("category");
        String country = req.getParameter("country");
        Product product = new Product(name, brand, description, price, category, country);
        long idProduct = productDAO.addProduct(product);
        List<Product> productList = productDAO.getAllProducts();
        req.setAttribute("idProduct", idProduct);
        String message = "The new product has been successfully created.";
        req.setAttribute("message", message);
        forwardListProducts(req, resp, productList);
    }

    private void editProductAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String brand = req.getParameter("brand");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        String category = req.getParameter("category");
        String country = req.getParameter("country");
        long idProduct = Integer.valueOf(req.getParameter("idProduct"));
        Product product = new Product(name, brand, description, price, category, country, idProduct);
        product.setId(idProduct);
        boolean success = productDAO.updateProduct(product);
        String message = null;
        if (success) {
            message = "The Product has been successfully updated.";
        }
        List<Product> productList = productDAO.getAllProducts();
        req.setAttribute("idProduct", idProduct);
        req.setAttribute("message", message);
        forwardListProducts(req, resp, productList);
    }

    private void removeProductByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long idProduct = Integer.valueOf(req.getParameter("idProduct"));
        boolean confirm = productDAO.deleteProduct(idProduct);
        if (confirm){
            String message = "The product has been successfully removed.";
            req.setAttribute("message", message);
        }
        List<Product> productList = productDAO.getAllProducts();
        forwardListProducts(req, resp, productList);
    }

}
