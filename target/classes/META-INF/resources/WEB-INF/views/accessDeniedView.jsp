<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.Writer" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.11.2021
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>Access Denied</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<c: Writer writer = response.getWriter();
    wr%>
<h3>Product List</h3>

<p style="color: red;">${errorString}</p>

<br/>
<a href="createProduct" >Create Product</a>
<br/>

<jsp:include page="_accessDenied.jsp"></jsp:include>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>