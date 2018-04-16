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
    <form method="post" action="addUser">
        <div class="form-group">
            <label>Name</label>
            <input class="form-control" name="name" placeholder="Name">
        </div>
        <div class="form-group">
            <label>Name</label>
            <input class="form-control" name="login" placeholder="Login">
        </div>
        <div class="form-group">
            <label>Name</label>
            <input class="form-control" name="password" placeholder="Password">
        </div>
        <div class="form-group">
            <label>Name</label>
            <input class="form-control" name="email" placeholder="Email">
        </div>
        <div class="form-group">
            <label>Name</label>
            <input class="form-control" name="city" placeholder="City">
        </div>
        <div class="form-group">
            <label>Name</label>
            <input class="form-control" name="role" placeholder="Role">
        </div>
        <input class="btn btn-default btn-xs" type="submit" value="Save">
        <a class="btn btn-default btn-xs" href="usersList" role="button">cancel</a>
    </form>
</div>
</body>
</html>
