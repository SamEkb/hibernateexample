<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>

<h1>Hello USER!</h1>
<div class="container">
    <h1><p class="text-center">User</p></h1>
    <table class="table">
        <thead>
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Login</td>
            <td>Password</td>
            <td>Email</td>
            <td>City</td>
            <td>Role</td>
            <td>Registration date</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.city}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td><c:out value="${user.registrationDate}"/></td>
            <td>
                <form method="post" action="/purses">
                    <input type="number" hidden name="id" value="${user.id}"/>
                    <input type="submit" class="btn btn-danger btn-xs" name="delete" value="Purses"/>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
    <p align="left"><a class="btn btn-info btn-xs" href="userPage" role="button">Back</a>
</div>
</body>
</html>