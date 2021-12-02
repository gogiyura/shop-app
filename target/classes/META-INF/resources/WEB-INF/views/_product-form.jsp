<%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 01.12.2021
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.io.*, java.util.Locale" %>
<%@page import="javax.servlet.*, javax.servlet.http.*" %>


<div style="padding: 5px;">
<h3>Add Product</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/createProduct">
    <table border="0">
        <tr>
            <td>#</td><td><input type="text" name="id" value="${product.id}" /></td>
        </tr>
        <tr>
            <td>Name</td><td><input type="text" name="name" value="${product.name}" /></td>
        </tr>
        <tr>
            <td>Price</td><td><input type="text" name="price" value="${product.price}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" /><a href="productList">Cancel</a></td>
        </tr>
    </table>
</form>

</div>

