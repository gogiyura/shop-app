<%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 29.11.2021
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Hello: ${user.name}</h3>

User Name: <b>${user.name}</b>
<br />
email: ${user.email } <br />

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>