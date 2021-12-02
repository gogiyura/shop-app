<%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 01.12.2021
  Time: 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="shop" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>JSP/JSTL i18n demo</title>
</head>
<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="nl" ${language == 'ru' ? 'selected' : ''}>Русский</option>
        <option value="es" ${language == 'uk' ? 'selected' : ''}>Українська</option>
    </select>
</form>
<form method="post">
    <label for="username"><fmt:message key="enterName" />:</label>
    <input type="text" id="username" name="username">
    <br>
    <label for="password"><fmt:message key="enterPassword" />:</label>
    <input type="password" id="password" name="password">
    <br>
    <fmt:message key="login" var="buttonValue" />
    <input type="submit" name="submit" value="${buttonValue}">
</form>
</body>
</html>