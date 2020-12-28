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

<div align="right" class="container">
    <form method="POST" action="/?command=changeLanguage" accept-charset="UTF-8">
        <select name="lang">
            <option value="eng">English</option>
            <option value="ukr">Українська</option>
        </select>
        <p><input type="submit" value="${changeLanguageButton}"/></p>
    </form>
</div>

<div class="container">
    <div class="row">
        <div class="span4 offset4 well">
            <legend>${signInLabel}</legend>
            <div class="alert alert-error">
                <c:out value="${authError}"/>
            </div>
            <form method="POST" action="/?command=authorization" accept-charset="UTF-8">
                <input type="text" id="username" class="span4" name="login" placeholder="${loginLabel}">
                <input type="password" id="password" class="span4" name="password" placeholder="${passwordLabel}">

                <button type="submit" name="submit" class="btn btn-info btn-block">${signInButton}</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
