<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>User list</h2>

<c:out value="${info}"/>
<br/>
<br/>
Список пользователей:
<c:forEach var="user" items="${userList}">
    <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>
    <p> ${user.id==userid?'(*)':null} ${user.id} / ${user.name} / ${user.email} </p>
</c:forEach>


</body>
</html>
