<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.11.2021
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1>My Site</h1>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">

        <!-- User store in session with attribute: loginedUser -->
        Hello, <b>${loginedUser.name} </b>
        <br/>
        Search <input name="search">

    </div>

</div>
