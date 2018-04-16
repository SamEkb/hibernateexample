<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h1><p class="text-center">All Currencies</p></h1>
    <p>
    <a class="btn btn-info btn-xs" href="/usersList" role="button">All users</a>
    </p>
    <table class="table">
        <thead>
        <tr>
            <td>Id</td>
            <td>Name</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="currency" items="${currencies}">
            <tr>
                <td>${currency.id}</td>
                <td>${currency.name}</td>
                <td>
                    <form method="get" action="updateCurrency">
                        <input type="number" hidden name="id" value="${currency.id}"/>
                        <input type="submit" class="btn btn-default btn-xs" name="edit" value="Edit"/>
                    </form>
                    <form method="post" action="/deleteCurrency">
                        <input type="number" hidden name="id" value="${currency.id}"/>
                        <input type="submit" class="btn btn-danger btn-xs" name="delete" value="Delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p align="left"><a class="btn btn-info btn-xs" href="/" role="button">Logout</a>
    <p align="right"><a class="btn btn-info btn-xs" href="addCurrency" role="button">Add currency</a>
</div>
</body>
</html>
