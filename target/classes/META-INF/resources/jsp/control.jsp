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
    <title>Employees</title>
</head>

<body>
<div class="container">
    <h2>Control panel</h2>
    <br>
</div>

<!-- Nav tabs -->
<jsp:include page="../jspf/navbar.jsp" />
<div class="container mt-3">

    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" data-bs-toggle="tab" href="#orders">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-bs-toggle="tab" href="#products">Menu 1</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-bs-toggle="tab" href="#users">Menu 2</a>
        </li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">

        <div id="orders" class="container tab-pane active"><br>
            <h3>Orders</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>


        </div>

        <div id="products" class="container tab-pane fade"><br>
            <h3>Products</h3>
            <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>


        </div>

        <div id="users" class="container tab-pane fade"><br>
            <h3>Users</h3>
            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>


        </div>

    </div>
</div>
<jsp:include page="../jspf/footer.jsp" />
</body>
</html>
