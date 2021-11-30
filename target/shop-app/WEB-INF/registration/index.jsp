<%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 30.11.2021
  Time: 4:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="process.jsp">
    <input type="text" name="uname" value="Name..." onclick="this.value=''"/><br/>
    <input type="text" name="uemail"  value="Email ID..." onclick="this.value=''"/><br/>
    <input type="password" name="upass"  value="Password..." onclick="this.value=''"/><br/>
    <input type="submit" value="register"/>
</form>