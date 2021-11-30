<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.11.2021
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>Order List</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Order List</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th><a href="">Code</a></th>
        <th>Created</th>
        <th>Holder</th>
        <th>Price</th>
        <th>Open</th>
        <th>Exterminate</th>
    </tr>
    <c:forEach items="${orderList}" var="order" >
        <tr>
            <td>${order.code}</td>
            <td>${order.user_id}</td>
            <td>
                <a href="openOrder?code=${order.code}">Edit</a>
                <a href="/order" />
            </td>
            <td>
                <a href="cancelOrder?code=${order.code}">Exterminate</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="createProduct" >Create Product</a>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>