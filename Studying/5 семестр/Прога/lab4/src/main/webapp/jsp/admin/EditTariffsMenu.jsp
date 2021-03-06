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
    <title>Edit tariffs</title>
</head>
<body>
<h3>
    <ol>
        <li><a href="<c:url value='/admin/edit-tariffs/add-tariff' />">${addTariffMenuItem}</a></li>
        <li><a href="<c:url value='/?command=loadTariffs&urlToForward=/admin/edit-tariffs/delete-tariff' />">${deleteTariffMenuItem}</a></li>
        <li><a href="<c:url value='/?command=loadTariffs&urlToForward=/admin/edit-tariffs/edit-tariff' />">${editTariffMenuItem}</a></li>
        <li><a href="<c:url value='/show-tariffs' />">${showTariffsMenuItem}</a></li>
    </ol>
</h3>
<form>
    <input type="button" value="${backLabel}" onClick='location.href="/admin/main-menu"'>
</form>
</body>
</html>
