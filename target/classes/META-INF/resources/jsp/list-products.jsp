<%@ page import="com.example.model.entity.Product" %><%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 26.11.2021
  Time: 1:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>Catalog</title>
</head>

<body>
<jsp:include page="../jspf/navbar.jsp" />

<div class="container mt-5">
    <h2>Products</h2>
    <!--Search Form -->
    <form action="/catalog" method="get" id="seachProductForm" role="form">
        <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
        <div class="input-group mb-3 mt-3">
            <input type="text" name="productName" id="productName" class="form-control" required="true" placeholder="Type the Name or Brand of the product"/>
            <button class="btn btn-outline-primary" type="button">Search</button>
        </div>


        <br></br>
        <br></br>
    </form>


<!--Products List-->
<c:if test="${not empty message}">
    <div class="alert alert-success">
            ${message}
    </div>
</c:if>
<form action="/catalog" method="post" id="productForm" role="form" >
    <input type="hidden" id="idProduct" name="idProduct">
    <input type="hidden" id="action" name="action">
    <c:choose>
        <c:when test="${not empty productList}">
            <table  class="table table-striped">
                <thead>
                <tr>
                    <td>#</td>
                    <% Product.getTableHeaderRow(); %>
                    <td></td>
                </tr>
                </thead>
                <c:forEach var="product" items="${productList}">
                    <c:set var="classSucess" value=""/>
                    <c:if test ="${idProduct == product.id}">
                        <c:set var="classSucess" value="info"/>
                    </c:if>
                    <tr class="${classSucess}">
                        <td>
                            <a href="/product?idProduct=${product.id}&searchAction=searchById">${product.id}</a>
                        </td>
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>${product.brand}</td>
                        <td>${product.description}</td>
                        <td>${product.country}</td>
                        <td><a href="#" id="remove"
                               onclick="document.getElementById('action').value = 'remove';
                               document.getElementById('idProduct').value = '${product.id}';
                               document.getElementById('productForm').submit();">
                            <span class="glyphicon glyphicon-trash"/>
                        </a>

                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-info">
                No products found matching your search criteria
            </div>
        </c:otherwise>
    </c:choose>
</form>
    <form action ="jsp/new-product.jsp">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md">New product</button>
    </form>
</div>
<jsp:include page="../jspf/footer.jsp" />
</body>
</html>