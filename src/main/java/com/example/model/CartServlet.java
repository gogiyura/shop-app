package com.example.model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(
        name = "CartServlet",
        urlPatterns = {"/cart"}
)
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        String name = req.getParameter("name");
        int quantity = Integer.parseInt(req.getParameter("quantity"));


        if (cart == null) {
            cart = new Cart();
        } else {

        }

    }

    private void forwardCart(HttpServletRequest req, HttpServletResponse resp, List employeeList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/show-cart.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

}
