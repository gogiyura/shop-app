<%--
  Created by IntelliJ IDEA.
  User: Георгий
  Date: 25.11.2021
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/bootstrap.min.js"></script>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar">1</span>
                <span class="icon-bar">2222222222</span>
                <span class="icon-bar"></span>
                <span class="glyphicon glyphicon-trash"/>
            </button>
            <a class="navbar-brand" href="/catalog">Brand</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/catalog">Catalog <span class="sr-only">(current)</span></a></li>
                <li><a href="/employee">Employee</a></li>

            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Search</button>
            </form>
            <ul class="nav navbar-nav navbar-right">

                <li class="nav-item dropdown">
                    <a id="dLabel" href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Menu <span class="caret"></span></a>
                    <ul class="dropdown-menu" aria-labelledby="dLabel">
                        <li><a href="/employee">Employees</a></li>
                        <li><a href="/catalog">Catalog</a></li>
                        <li><a href="/users">Users</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/Cabinet">Cabinet</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/logout">Log out</a></li>
                    </ul>
                </li>
                <li><a href="/login">Log in</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
