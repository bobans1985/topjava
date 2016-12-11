<%--
  Created by IntelliJ IDEA.
  User: bobans
  Date: 10.12.16
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add meal</title>
</head>
<h2><a href="index.html">Home</a></h2>
<h1>Add meal</h1>
<form method="post" action="meals">
    <input type="hidden" name="type" value="add">
    <p>Дата:<input type="datetime-local" name="Date"></p>
    <p>Описание:<input type="text" name="Description"></p>
    <p>Калории:<input type="number" name="Calories"></p>
    <p><button>Create</button></p>
</form>


</body>
</html>
