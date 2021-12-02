<%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 01.12.2021
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.io.*, java.util.Locale" %>
<%@page import="javax.servlet.*, javax.servlet.http.*" %>


<div style="padding: 5px;">
    <h3>Product List</h3>

    <p style="color: red;">${errorString}</p>
    <hr />
    <table border="5" cellpadding="5" cellspacing="5" >

        <tr><th><a href="">#</a></th><th>Name</th><th>Price</th><th>Edit/delete</th></tr>

        <c:forEach items="${productList}" var="product" ><tr>

            <td>${product.id}</td><td>${product.name}</td><td>${product.price}</td>

                <td><a href="editProduct?code=${product.id}">Edit</a>
                    /
                    <a href="deleteProduct?code=${product.id}">delete</a>
                </td>
        </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="createProduct" >Create Product</a>
    <br/>
</div>
