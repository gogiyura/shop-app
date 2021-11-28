<%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 25.11.2021
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<!-- A grey horizontal navbar that becomes vertical on small screens -->

<nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">

    <div class="container">
        <!-- Links -->


        <ul class="navbar-nav mb-3">
            <li class="nav-item">
                <a class="navbar-brand" href="/">Logo</a>
            </li>

            <li class="nav-item">
            <form class="d-flex">
                <input class="form-control me-2" type="text" placeholder="Search">
                <button class="btn btn-primary" type="button">Search</button>
            </form>
            </li>

        </ul>
        <button type="button" class="btn btn-outline-light  float-end" data-bs-toggle="modal" data-bs-target="#myModal">
            Log in
        </button>
    </div>
</nav>



<!-- The Modal -->
<div class="modal" id="myModal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Log in</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <div class="container mt-4">

                    <form action="/action_page" class="was-validated">

                        <div class="mb-3 mt-3">
                            <label for="uname" class="form-label">Username:</label>
                            <input type="text" class="form-control" id="uname" placeholder="Enter username" name="uname" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>


                        <div class="mb-3">
                            <label for="pwd" class="form-label">Password:</label>
                            <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pswd" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>


                        <div class="form-check mb-3">
                            <input class="form-check-input" type="checkbox" id="myCheck"  name="remember" required>
                            <label class="form-check-label" for="myCheck">I agree on blabla.</label>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Check this checkbox to continue.</div>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>



        </div>
    </div>
</div>

