<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="block">
    <div class="blockCalories">
        <h2><a href="startPage.html">Calories Counter</a></h2>
    </div>
    <h3>Meal list User</h3>

    <div class="blockMeal">
        <form method="post" action="meals?action=filter">
            <dl>
                <dt>From Date:</dt>
                <dd><input type="date" name="startDate" value="${startDate}"></dd>
            </dl>
            <dl>
                <dt>To Date:</dt>
                <dd><input type="date" name="endDate" value="${endDate}"></dd>
            </dl>
            <dl>
                <dt>From Time:</dt>
                <dd><input type="time" name="startTime" value="${startTime}"></dd>
            </dl>
            <dl>
                <dt>To Time:</dt>
                <dd><input type="time" name="endTime" value="${endTime}"></dd>
            </dl>
            <button type="submit">Filter</button>
        </form>
    </div>
    <p></p>
    <a href="meals?action=create">Add Meal</a>
    <p></p>
    <table class="tableBlue" cellspacing="5">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="com.lapeevvd.dataTransferObject.UserMealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                    <fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDate"/>
                    <fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm"/>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
