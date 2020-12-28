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
    <p><c:out value="${subscriberNameLabel}: ${currentUserInfo.getName()}"/></p>
    <p><c:out value="${activeStatusLabel}: ${currentUserInfo.isActive()}"/></p>
    <p><c:out value="${balanceLabel}: ${currentUserInfo.getFormattedBalance()}"/></p>
    <p><c:out value="${subscriberLoginLabel}: ${currentUserInfo.getLogin()}"/></p>
    <p></p>
    <p>${userTariffsLabel}:</p>

    <ul>
        <c:forEach var="tariff" items="${currentUserTariffs}">
            <li>
                    ${tariff.getName()}(${tariff.getServiceType()}), ${tariffPriceLabel}: ${tariff.getFormattedPrice()}
            </li>
        </c:forEach>
    </ul>

    <input type="button" value="${backLabel}" onClick='location.href="/user/main-menu"'>
</h3>
</body>
</html>
