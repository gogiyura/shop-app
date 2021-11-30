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
        <th>email</th>
        <th>password</th>
        <th></th>
    </tr>
    <c:forEach items="${userList}" var="user" >
        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>

            <td>
                <a href="switch?code=${suser.code}">Block</a>
            </td>

        </tr>
    </c:forEach>
</table>


<jsp:include page="_footer.jsp"></jsp:include>




</body>
</html>