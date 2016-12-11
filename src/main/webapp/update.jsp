<%--
  Created by IntelliJ IDEA.
  User: bobans
  Date: 11.12.16
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add meal</title>
</head>
<h2><a href="index.html">Home</a></h2>
<h1>Update meal</h1>
<form method="post" action="meals">
    <input type="hidden" name="type" value="update">
    <input type="hidden" name="id" value="<%=request.getAttribute("id")%>">
    <p>Дата:<input type="datetime-local" name="Date" value="<%=request.getAttribute("Date")%>"></p>
    <p>Описание:<input type="text" name="Description" value="<%=request.getAttribute("Description")%>"></p>
    <p>Калории:<input type="number" name="Calories" value="<%=request.getAttribute("Calories")%>"></p>
    <p><button>Update</button></p>
</form>


</body>
</html>