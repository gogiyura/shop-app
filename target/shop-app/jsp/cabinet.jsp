<%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 27.11.2021
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>Cabinet</title>
</head>

<body>


<jsp:include page="../jspf/navbar.jsp" />

<div class="container mt-5">
<form action="/employee" method="post" id="employeeForm" role="form" >

    <input type="hidden" id="idEmployee" name="idEmployee">
    <input type="hidden" id="action" name="action">

    <c:choose>
        <c:when test="${not empty employeeList}">
            <table  class="table table-striped">
                <thead>
                <tr>
                    <td>#</td>
                    <td>Name</td>
                    <td>Last name</td>
                    <td>Birth date</td>
                    <td>Role</td>
                    <td>Department</td>
                    <td>E-mail</td>
                    <td></td>
                </tr>
                </thead>
                <c:forEach var="employee" items="${employeeList}">
                    <c:set var="classSucess" value=""/>
                    <c:if test ="${idEmployee == employee.id}">
                        <c:set var="classSucess" value="info"/>
                    </c:if>
                    <tr class="${classSucess}">
                        <td>
                            <a href="/employee?idEmployee=${employee.id}&searchAction=searchById">${employee.id}</a>
                        </td>
                        <td>${employee.firstName}</td>
                        <td>${employee.lastName}</td>
                        <td>${employee.birthDate}</td>
                        <td>${employee.role}</td>
                        <td>${employee.department}</td>
                        <td>${employee.email}</td>
                        <td><a href="#" id="remove"
                               onclick="document.getElementById('action').value = 'remove';
                                       document.getElementById('idEmployee').value = '${employee.id}';
                                       document.getElementById('employeeForm').submit();">
                            <span class="glyphicon glyphicon-trash"/>
                        </a>

                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <br>
            <div class="alert alert-light">
                There will be your orders
            </div>
        </c:otherwise>
    </c:choose>
</form>
</div>
<jsp:include page="../jspf/footer.jsp" />
</body>
</html>
