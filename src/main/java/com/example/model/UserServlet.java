package com.example.model;

import com.example.model.entity.Product;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet (
        name = "UserServlet",
        urlPatterns = {"/userlist"}
)
public class UserServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("searchAction");

            List<Product> result = ProductDAO.getAllProducts();
            forwardListUser(req, resp,result);

    }

    private void forwardListUser(HttpServletRequest req, HttpServletResponse resp, List productList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/list-products.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("productList", productList);
        dispatcher.forward(req, resp);
    }
}
