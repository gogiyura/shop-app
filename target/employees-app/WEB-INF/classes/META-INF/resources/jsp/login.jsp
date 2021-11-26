<%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 26.11.2021
  Time: 0:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <script src="../js/bootstrap.min.js"></script>
    <title>Log in</title>
</head>
<body>
<jsp:include page="../jspf/navbar.jsp" />

<div class="container">
    <div>
        <form action="/action_page.php">
            <div class="mb-3 mt-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
            </div>
            <div class="mb-3">
                <label for="pwd" class="form-label">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pswd">
            </div>
            <div class="form-check mb-3">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" name="remember"> Remember me
                </label>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
</div>
</div>
</body>
</html>
