<%--
  Created by IntelliJ IDEA.
  User: gaevo
  Date: 12/14/2020
  Time: 1:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<h3>
    <ol>
        <li><a href="<c:url value='/show-tariffs' />">${showTariffsMenuItem}</a></li>
        <li><a href="<c:url value='/?command=loadTariffs&urlToForward=/user/choose-tariff' />">${chooseTariffMenuItem}</a></li>
        <li><a href="<c:url value='/user/top-up' />">${depositMenuItem}</a></li>
        <li><a href="<c:url value='/?command=profileInfo' />">${showProfileMenuItem}</a></li>
    </ol>
</h3>
<form action="/?command=exit" method="post">
    <input type="submit" value="${exitLabel}"/>
</form>
</body>
</html>
