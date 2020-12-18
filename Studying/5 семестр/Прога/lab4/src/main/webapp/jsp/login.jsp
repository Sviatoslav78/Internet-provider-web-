<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>

    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet"
          id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="span4 offset4 well">
            <legend>Sign In</legend>
            <div class="alert alert-error">
                <c:out value="${authError}"/>
            </div>
            <form method="POST" action="/?command=authorization" accept-charset="UTF-8">
                <input type="text" id="username" class="span4" name="login" placeholder="Login">
                <input type="password" id="password" class="span4" name="password" placeholder="Password">

                <button type="submit" name="submit" class="btn btn-info btn-block">Sign in</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
