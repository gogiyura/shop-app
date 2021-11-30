<%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 30.11.2021
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>

<%@page import="bean.RegisterDao"%>
<jsp:useBean id="obj" class="bean.User"/>

<jsp:setProperty property="*" name="obj"/>

<%
    int status=RegisterDao.register(obj);
    if(status>0)
        out.print("You are successfully registered");

%>