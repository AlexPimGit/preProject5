<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Page</title>
</head>
<body>
<h3>User Page</h3>
<table border="1" cellpadding="5">
    <tr>
        <th>Name</th>
        <th>Nickname</th>
        <th>Role</th>
        <th>Password</th>
    </tr>
    <tr>
        <th><c:url value="${loginUserName}"/></th>
        <th><c:url value="${loginUserNickname}"/></th>
        <th><c:url value="${loginUserRole}"/></th>
        <th><c:url value="${loginUserPassword}"/></th>
    </tr>
</table>
<form action="Logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>