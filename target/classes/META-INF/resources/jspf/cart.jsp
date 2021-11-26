<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.11.2021
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.Cart" %>
<% Cart cart = (Cart) session.getAttribute("cart"); %>
<c:forEach var="key" items="${cart.getCart()}">
    <p><c:out value="key" /> </p>
</c:forEach>
