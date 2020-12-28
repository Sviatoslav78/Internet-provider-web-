<%@ page import="org.application.model.entity.ServiceType" %><%--
  Created by IntelliJ IDEA.
  User: gaevo
  Date: 12/14/2020
  Time: 6:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>AddTariff</title>
</head>
<body>
<h3>
    <form action="/?command=addTariff" method="post">
        <p>${serviceTypeLabel}: <select name="serviceType">
            <option value="0">Phone</option>
            <option value="1">Internet</option>
            <option value="2">TV</option>
        </select></p>

        <p>${tariffNameLabel}: <input name="tariffName" type="text"/></p>
        <p>${tariffPriceLabel}: <input name="tariffPrice" type="number"/></p>
        <p><input type="submit" value="${addTariffButton}"/></p>

        <input type="button" value="${backLabel}" onClick='location.href="/admin/edit-tariffs"'>

        <p><c:out value="${addTariffResponse}"/></p>
    </form>
</h3>
</body>
</html>
