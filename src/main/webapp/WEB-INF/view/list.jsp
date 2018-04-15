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
    <h1><p class="text-center">All Purses</p></h1>
    <table class="table">
        <thead>
        <tr>
            <td>Id</td>
            <td>User id</td>
            <td>User name</td>
            <td>Currency</td>
            <td>Amount</td>
            <td>Created</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="purse" items="${purses}">
            <tr>
                <td>${purse.id}</td>
                <td>${purse.user.id}</td>
                <td>${purse.user.name}</td>
                <td>${purse.currency.name}</td>
                <td>${purse.amount}</td>
                <td>${purse.createDate}</td>
                <td>
                    <form method="get" action="/edit">
                        <input type="number" hidden name="id" value="${purse.id}"/>
                        <input type="submit" class="btn btn-default btn-xs" name="edit" value="Edit"/>
                    </form>
                    <form method="post" action="/delete">
                        <input type="number" hidden name="id" value="${purse.id}"/>
                        <input type="submit" class="btn btn-danger btn-xs" name="delete" value="Delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--<p align="left"><a class="btn btn-info btn-xs" href="create" role="button">Add User</a>--%>
    <%--<p align="left"><a class="btn btn-info btn-xs" href="/" role="button">Back</a></p>--%>
</div>
</body>
</html>
