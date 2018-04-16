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
<div class="container">
    <h1>Save currency</h1>
    <form method="post" action="updateCurrency">
        <input class="form-control" name="id" value="${currency.id}" type="hidden">

        <div class="form-group">
            <label>Name</label>
            <input class="form-control" name="name" placeholder="Name" value="${currency.name}">
        </div>
        <input class="btn btn-default btn-xs" type="submit" value="save">
        <a class="btn btn-default btn-xs" href="currencyList" role="button">cancel</a>
    </form>
</div>
</body>
</html>
