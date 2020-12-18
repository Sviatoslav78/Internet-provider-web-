<%--
  Created by IntelliJ IDEA.
  User: gaevo
  Date: 12/14/2020
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Tariff</title>
</head>
<body>
<h3>
    <form action="/?command=editTariff" method="post">

        <p>Tariff name: <input name="tariffName" type="text"/></p>
        <p>Tariff price: <input name="tariffPrice" type="number" /></p>
        <p><input type="submit" value="Edit tariff"/></p>

        <input type="button" value="Back" onClick='location.href="/admin/edit-tariffs"'>

        <p><c:out value="${editTariffResponse}"/></p>
    </form>
</h3>
</body>
</html>
