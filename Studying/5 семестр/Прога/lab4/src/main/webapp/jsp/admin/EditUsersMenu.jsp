<%--
  Created by IntelliJ IDEA.
  User: gaevo
  Date: 12/14/2020
  Time: 5:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit subscribers</title>
</head>
<body>
<h3>
    <ol>
        <li><a href="<c:url value='/admin/edit-users/register-user' />">${registerMenuItem}</a></li>
        <li><a href="<c:url value='/admin/edit-users/change-status' />">${changeStatusMenuItem}</a></li>
        <li><a href="<c:url value='/admin/edit-users/show-users' />">${showUsersMenuItem}</a></li>
    </ol>
</h3>
<form>
    <input type="button" value="${backLabel}" onClick='location.href="/admin/main-menu"'>
</form>
</body>
</html>
