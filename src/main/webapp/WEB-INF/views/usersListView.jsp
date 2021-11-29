<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.11.2021
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>User List</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Gender</th>
        <th>role</th>
        <th>block</th>

        <th>Add to cart</th>
    </tr>
    <c:forEach items="${userList}" var="user" >
        <tr>
            <td>${user.id}</td>
            <td>${user.userName}</td>
            <td>${user.gender}</td>
            <td>${user.role}</td>
            <td><a href="switch?code=${user.code}">${user.isBlocked}</a></td>
            <td>
                <a href="deleteProduct?code=${product.code}">Delete</a>
            </td>
            <td>
                <a href="addToCart?code=${product.code}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="createProduct" >Create Product</a>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>