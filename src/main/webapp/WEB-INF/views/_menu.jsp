<%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 29.11.2021
  Time: 8:23
  To change this template use File | Settings | File Templates.
--%>
<a href="${pageContext.request.contextPath}/employeeTask">
    Employee Task
</a>
||
<a href="${pageContext.request.contextPath}/managerTask">
    Manager Task
</a>
||
<a href="${pageContext.request.contextPath}/userInfo">
    User Info
</a>
||
<a href="${pageContext.request.contextPath}/login">
    Login
</a>
||
<a href="${pageContext.request.contextPath}/logout">
    Logout
</a>

&nbsp;
<span style="color:red">[ ${loginedUser.userName} ]</span>