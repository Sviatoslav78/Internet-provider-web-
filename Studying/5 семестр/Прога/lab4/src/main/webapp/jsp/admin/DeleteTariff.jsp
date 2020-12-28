<%--
  Created by IntelliJ IDEA.
  User: gaevo
  Date: 12/14/2020
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Delete Tariff</title>
</head>
<body>
<h3>
    <form action="/?command=deleteTariff" method="post">

        <p>${tariffNameLabel}:
            <select name="tariffName">
                <c:forEach var="tariff" items="${currentTariffs}">
                    <option value="${tariff.getName()}">${tariff.getName()}(${tariff.getServiceType()}),
                        price:${tariff.getFormattedPrice()}</option>
                </c:forEach>
            </select>
        </p>
        <p><input type="submit" value="${deleteTariffButton}"/></p>

        <input type="button" value="${backLabel}" onClick='location.href="/admin/edit-tariffs"'>

        <p><c:out value="${deleteTariffResponse}"/></p>
    </form>
</h3>
</body>
</html>
