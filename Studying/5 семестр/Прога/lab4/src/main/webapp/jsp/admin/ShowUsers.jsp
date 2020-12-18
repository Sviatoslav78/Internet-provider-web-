<%--
  Created by IntelliJ IDEA.
  User: gaevo
  Date: 12/17/2020
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Show Users</title>

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
    <c:param name="command" value="showUsers"/>
</c:url>
<h2><a href="<c:url value='${myURL}' />">Show subscribers</a></h2>

<table id="myTable" class="display">
    <thead>
    <tr>
        <th>Name</th>
        <th>Active</th>
        <th>Balance</th>
        <th>Login</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="user" items="${availableUsers}">
        <tr>
            <td>${user.getName()}</td>
            <td>${user.isActive()}</td>
            <td>${user.getBalance()}</td>
            <td>${user.getLogin()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<input type="button" value="Back" onClick='location.href="/admin/edit-users"'>

</body>
</html>
