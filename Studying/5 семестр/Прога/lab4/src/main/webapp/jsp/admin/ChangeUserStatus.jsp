<%--
  Created by IntelliJ IDEA.
  User: gaevo
  Date: 12/17/2020
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Change Status</title>
</head>
<body>
<h3>
    <form action="/?command=changeStatus" method="post">
        <p>${subscriberLoginLabel}: <input name="subscriberLogin" type="text"/></p>

        <p>${subscriberStatusLabel}: <select name="status">
            <option value="unblock">${unblockStatus}</option>
            <option value="block">${blockStatus}</option>
        </select></p>
        <p><input type="submit" value="${updateStatusButton}"/></p>

        <input type="button" value="${backLabel}" onClick='location.href="/admin/edit-users"'>

        <p><c:out value="${changeStatusResponse}"/></p>
    </form>
</h3>
</body>
</html>
