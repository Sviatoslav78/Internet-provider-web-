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
    <title>Show Tariffs</title>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#myTable").DataTable();
        })
    </script>
</head>
<body>

<c:url value="/" var="myURL">
    <c:param name="command" value="showTariffs"/>
</c:url>
<h2><a href="<c:url value='${myURL}' />">${showTariffsMenuItem}</a></h2>

<table id="myTable" class="display">
    <thead>
    <tr>
        <th>${serviceTypeLabel}</th>
        <th>${tariffNameLabel}</th>
        <th>${tariffPriceLabel}</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="tariff" items="${availableTariffs}">
        <tr>
            <td>${tariff.getServiceType()}</td>
            <td>${tariff.getName()}</td>
            <td>${tariff.getFormattedPrice()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<input type="button" value="${backLabel}" onClick='location.href="/${currentUser}/edit-tariffs"'>

</body>
</html>
