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

        <p>Deposit sum: <input name="depositSum" type="number" /></p>
        <p><input type="submit" value="Top up account" /></p>
        <input type="button" value="Back" onClick='location.href="/user/main-menu"'>

        <p> <c:out value="${depositCommandResponse}" /> </p>
    </form>
</h3>
</body>
</html>
