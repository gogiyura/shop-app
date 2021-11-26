<%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 26.11.2021
  Time: 5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../jspf/navbar.jsp" />
<div class="container">
    <form action="/catalog" method="post"  role="form" data-toggle="validator" >
        <c:if test ="${empty action}">
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idProduct" name="idProduct" value="${product.id}">
        <h2>Product</h2>
        <div class="form-group col-xs-4">
            <label for="name" class="control-label col-xs-4">Name:</label>
            <input type="text" name="name" id="name" class="form-control" value="${product.name}" required="true"/>

            <label for="brand" class="control-label col-xs-4">Brand:</label>
            <input type="text" name="brand" id="brand" class="form-control" value="${product.brand}" required="true"/>

            <label for="description" class="control-label col-xs-4">Description:</label>
            <input type="text" name="description" id="description" class="form-control" value="${product.description}" required="true"/>

            <label for="role" class="control-label col-xs-4">Price:</label>
            <input type="text" name="role" id="role" class="form-control" value="${product.price}" required="true"/>

            <label for="imageURL" class="control-label col-xs-4">Image URL:</label>
            <input type="text" name="imageURL" id="imageURL" class="form-control" value="${product.imageURL}" required="true"/>

            <label for="category" class="control-label col-xs-4">Category:</label>
            <input type="text" name="category" id="category" class="form-control" value="${product.category}" required="true"/>

            <label for="country" class="control-label col-xs-4">Country:</label>
            <input type="text" name="country" id="country" class="form-control" value="${product.country}" required="true"/>

            <br></br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>
