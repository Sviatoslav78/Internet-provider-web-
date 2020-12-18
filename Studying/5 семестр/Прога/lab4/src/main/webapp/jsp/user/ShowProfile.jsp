<%--
  Created by IntelliJ IDEA.
  User: gaevo
  Date: 12/18/2020
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h3>
    <p><c:out value="Name: ${currentUserInfo.getName()}"/></p>
    <p><c:out value="Active: ${currentUserInfo.isActive()}"/></p>
    <p><c:out value="Balance: ${currentUserInfo.getBalance()}"/></p>
    <p><c:out value="Login: ${currentUserInfo.getLogin()}"/></p>

    <input type="button" value="Back" onClick='location.href="/user/main-menu"'>
</h3>
</body>
</html>
