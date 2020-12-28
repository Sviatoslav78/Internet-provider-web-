<%--
  Created by IntelliJ IDEA.
  User: gaevo
  Date: 12/17/2020
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Top up</title>
</head>
<body>
<h3>
    <form action="/?command=deposit" method="post">

        <p>${depositSumLabel}: <input name="depositSum" type="number"/></p>
        <p><input type="submit" value="${depositMenuItem}"/></p>
        <input type="button" value="${backLabel}" onClick='location.href="/user/main-menu"'>

        <p><c:out value="${depositCommandResponse}"/></p>
    </form>
</h3>
</body>
</html>
