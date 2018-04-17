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
    <h1><p class="text-center">All Users</p></h1>
    <p>
        <a class="btn btn-info btn-xs" href="/currencyList" role="button">All Currency</a>
    </p>
    <table class="table">
        <thead>
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Login</td>
            <td>Password</td>
            <td>email</td>
            <td>City</td>
            <td>Role</td>
            <td>Registration date</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.email}</td>
                <td>${user.city}</td>
                <td>${user.role}</td>
                <td>${user.registrationDate}</td>
                <td>
                    <form method="get" action="/editUser">
                        <input type="number" hidden name="id" value="${user.id}"/>
                        <input type="submit" class="btn btn-default btn-xs" name="edit" value="Edit"/>
                    </form>
                    <form method="post" action="/deleteUser">
                        <input type="number" hidden name="id" value="${user.id}"/>
                        <input type="submit" class="btn btn-danger btn-xs" name="delete" value="Delete"/>
                    </form>
                    <form method="post" action="/purses">
                        <input type="number" hidden name="id" value="${user.id}"/>
                        <input type="submit" class="btn btn-danger btn-xs" name="delete" value="Purses"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p align="left"><a class="btn btn-info btn-xs" href="/" role="button">Back</a>
    <p align="right"><a class="btn btn-info btn-xs" href="addUser" role="button">Add User</a>
</div>
</body>
</html>
