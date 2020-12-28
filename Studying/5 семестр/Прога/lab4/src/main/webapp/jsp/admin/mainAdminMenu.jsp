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
    <title>Admin</title>
</head>
<body>
<h3>
    <ol>
        <li><a href="<c:url value='/admin/edit-tariffs' />">${editTariffsMenuItem}</a></li>
        <li><a href="<c:url value='/admin/edit-users' />">${editSubscribersMenuItem}</a></li>
    </ol>
</h3>

<form action="/?command=exit" method="post">
    <input type="submit" value="${exitLabel}"/>
</form>

</body>
</html>
