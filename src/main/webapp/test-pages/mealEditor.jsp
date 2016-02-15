<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../WEB-INF/jsp/fragments/headTag.jsp"/>
<body>
<jsp:include page="../WEB-INF/jsp/fragments/bodyHeader.jsp"/>
<h2><a href="${pageContext.request.contextPath}/">Home</a></h2>
<h3>Edit meal</h3>
<hr>
<jsp:useBean id="meal" type="com.lapeevvd.model.UserMeal" scope="request"/>
<form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}">
    <dl>
        <dt>DateTime:</dt>
        <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime"></dd>
    </dl>
    <dl>
        <dt>Description:</dt>
        <dd><input type="text" value="${meal.description}" size=40 name="description"></dd>
    </dl>
    <dl>
        <dt>Calories:</dt>
        <dd><input type="number" value="${meal.calories}" name="calories"></dd>
    </dl>
    <button type="submit">Save</button>
    <button onclick="window.history.back()">Cancel</button>
</form>
<jsp:include page="../WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>
