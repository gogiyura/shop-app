<%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 29.11.2021
  Time: 8:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.io.*, java.util.Locale" %>
<%@page import="javax.servlet.*, javax.servlet.http.*" %>



<div style="padding: 5px;">
    <a href="${pageContext.request.contextPath}/">Home</a>|
    <a href="${pageContext.request.contextPath}/productList">Product List</a>|
    <a href="${pageContext.request.contextPath}/userInfo">My Account Info</a>|
    <a href="${pageContext.request.contextPath}/login">Login</a>|
    <a href="${pageContext.request.contextPath}/logout">Logout</a>|
    <a href="${pageContext.request.contextPath}/registration">Registration</a>|
    <a href="${pageContext.request.contextPath}/userList">User List</a>|
    <a href="${pageContext.request.contextPath}/orderList">Order List</a>
</div>