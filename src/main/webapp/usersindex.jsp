<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>



    <style>
        .fig {
            text-align: center; /* Выравнивание по центру */
        }
    </style>

    <style>
        body {
            background-image: url(<c:url value="/1.jpg"/>);
        }
    </style>
</head>
<body bgcolor="#00CED1">
<h2>Users List</h2>
<p><a href='<c:url value="/create" />'>Create new</a></p>
<table>
    <tr>
        <th>Name</th>
        <th>Nickname</th>
        <th></th>
    </tr>

    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.name}</td>
            <td>${user.nickname}</td>
            <td>
                <a href='<c:url value="/edit?id=${user.id}" />'>Edit</a>

                <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>

</table>
<p class="fig"><img src="<c:url value="1.jpg"/>" width="1024" height="334" alt="image1"></p>
<h6>&#169; 2019 SPstudio for JM. ALL RIGHTS RESERVED.</h6>
</body>
</html>