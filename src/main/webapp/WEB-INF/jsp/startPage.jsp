<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <p/>

        <form method="post" action="users">
            <fmt:message key="root.login"/>: <select name="userId">
            <option value="100000" selected>User</option>
            <option value="100001">Admin</option>
        </select>
            <button type="submit">OK</button>
        </form>
        <ul>
            <li><a href="users"><fmt:message key="root.userlist"/></a></li>
            <li><a href="meals"><fmt:message key="root.meals"/></a></li>
        </ul>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>