<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Login Page</title>
</head>

<h2>Hello, please log in:</h2>
<br><br>
<form action='<c:url value="/"/>' method=post>
    <p><strong>Please enter your Name: </strong>
        <input type="text" name="name" size="25">
    <p>
    <p><strong>Please enter your password: </strong>
        <input type="password" size="15" name="password">
    <p>
    <p>
        <input type="submit" value="Submit">
        <input type="reset" value="Reset">
</form>
</html>