<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h1>Save User</h1>
    <form method="post" action="addPurse">
        <div class="form-group">
            <label>Currency</label>
            <select id="currency" name="currency">
                <c:forEach var="currency" items="${currencies}">
                    <option value="${currency.id}">${currency.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Amount</label>
            <input class="form-control" name="amount" placeholder="Amount">
        </div>
        <input class="btn btn-default btn-xs" type="submit" value="Save">
        <a class="btn btn-default btn-xs" href="userPurses" role="button">cancel</a>
    </form>
</div>
</body>
</html>