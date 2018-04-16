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
    <h1><p class="text-center">User's Purses</p></h1>
    <table class="table">
        <thead>
        <tr>
            <td>User Name</td>
            <td>Currency</td>
            <td>Amount</td>
            <td>Create date</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="purs" items="${purses}">
            <tr>
                <td>${purs.user.name}</td>
                <td>${purs.currency.name}</td>
                <td>${purs.amount}</td>
                <td>${purs.createDate}</td>
                <td>
                    <form method="get" action="/edit">
                        <input type="number" hidden name="id" value="${purs.id}"/>
                        <input type="submit" class="btn btn-default btn-xs" name="edit" value="Edit"/>
                    </form>
                    <form method="post" action="/delete">
                        <input type="number" hidden name="id" value="${purs.id}"/>
                        <input type="submit" class="btn btn-danger btn-xs" name="delete" value="Delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p align="left"><a class="btn btn-info btn-xs" href="usersList" role="button">Back</a></p>
</div>
</body>
</html>
