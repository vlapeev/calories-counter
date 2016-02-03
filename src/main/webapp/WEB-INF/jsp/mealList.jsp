<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="block">
    <div class="blockCalories">
        <h2><a href="/"><fmt:message key="app.title"/></a></h2>
    </div>
    <h3><fmt:message key="mealList.title"/></h3>

    <div class="blockMeal">
        <form method="post" action="meals/filter">
            <dl>
                <dt><fmt:message key="mealList.fromDate"/></dt>
                <dd><input type="date" name="startDate" value="${startDate}"></dd>
            </dl>
            <dl>
                <dt><fmt:message key="mealList.toDate"/></dt>
                <dd><input type="date" name="endDate" value="${endDate}"></dd>
            </dl>
            <dl>
                <dt><fmt:message key="mealList.fromTime"/></dt>
                <dd><input type="time" name="startTime" value="${startTime}"></dd>
            </dl>
            <dl>
                <dt><fmt:message key="mealList.toTime"/></dt>
                <dd><input type="time" name="endTime" value="${endTime}"></dd>
            </dl>
            <button type="submit">Filter</button>
        </form>
    </div>
    <p></p>
    <a href="meals/create"><fmt:message key="mealList.add"/></a>
    <p></p>
    <table class="tableBlue" cellspacing="5">
        <thead>
        <tr>
            <th><fmt:message key="mealList.date"/></th>
            <th><fmt:message key="mealList.desc"/></th>
            <th><fmt:message key="mealList.calories"/></th>
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
                <td><a href="meals/update?id=${meal.id}">Update</a></td>
                <td><a href="meals/delete?id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
