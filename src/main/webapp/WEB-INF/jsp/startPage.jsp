<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="block">

    <div class="blockCalories">
        <p></p>

        <h2><fmt:message key="app.title"/></h2>
    </div>
    <p></p>

    <form method="post" action="users">
        <fmt:message key="root.login"/> <label>
        <select name="userId">
            <option value="100000" selected>User</option>
            <option value="100001">Admin</option>
        </select>
    </label>
        <button type="submit">Ok</button>
    </form>
    <p></p>
    <p></p>
    <form method="get" action="users">
        <button type="submit"><fmt:message key="root.userlist"/></button>
    </form>
    <p></p>

    <form method="get" action="meals">
        <button type="submit"><fmt:message key="root.meals"/></button>
    </form>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>