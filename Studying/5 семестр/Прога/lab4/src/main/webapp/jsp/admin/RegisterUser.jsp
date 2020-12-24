<%--
  Created by IntelliJ IDEA.
  User: gaevo
  Date: 12/17/2020
  Time: 8:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register User</title>
</head>
<body>
<h3>
    <form action="/?command=register" method="post">

        <p>Subscriber's name: <input name="subscriberName" type="text"/></p>
        <p><input type="submit" value="Register user"/></p>

        <input type="button" value="Back" onClick='location.href="/admin/edit-users"'>

        <p><c:out value="${registerUserResponse}"/></p>
    </form>
</h3>
<p><c:out value="${newSubscriber.getLogin()}"/></p>
<p><c:out value="${newSubscriber.getPassword()}"/></p>
</body>
</html>
