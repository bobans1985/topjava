<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.format.FormatStyle" %>
<%@ page import="java.time.ZonedDateTime" %>
<%@ page import="java.time.ZoneId" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<p>Первый вариант:</p>
<table border="1">
    <%
        List<MealWithExceed> meals = (List<MealWithExceed>) request.getAttribute("mealsList");
        System.out.println(meals);
        for (MealWithExceed meal : meals) {
            DateTimeFormatter fmt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
            //ZonedDateTime zdt = ZonedDateTime.of(meal.getDateTime(), ZoneId.of("Europe/Moscow"));
            String tagcolor = meal.isExceed() ? " bgcolor=\"red\" " : " bgcolor=\"green\" ";
            //out.print("<TR" + tagcolor + "><TD>" + fmt.format(zdt) + "</TD><TD>" + meal.getDescription() + "</TD><TD>" + meal.getCalories() + "</TD><TD>" + meal.isExceed() + "</TD><TR>");
            out.print("<TR" + tagcolor + "><TD>" + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(meal.getDateTime()) + "</TD><TD>" + meal.getDescription() + "</TD><TD>" + meal.getCalories() + "</TD><TD>" + meal.isExceed() + "</TD><TR>");
        }
    %>
</table>

<p>Второй вариант:</p>
<table border="1">
    <c:forEach var="meal" items="<%=meals%>">
        <c:choose>
            <c:when test="${meal.exceed}"><TR bgcolor="red"></c:when>
            <c:otherwise><TR bgcolor="green"></c:otherwise>
        </c:choose>
       <%-- <TR bgcolor="green">
        <c:if test="${meal.exceed}">
            <TR bgcolor="red">
        </c:if>
        ${meal.exceed?'TEST':'not test'}--%>

        <td>${DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(meal.dateTime)}</td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        <td>${meal.exceed}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
