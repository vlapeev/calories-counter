<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <c:choose>
            <c:when test="${register}"><legend><fmt:message key="profile.register"/></legend></c:when>
            <c:otherwise><legend><fmt:message key="profile.title"/></legend></c:otherwise>
        </c:choose>
        <form:form modelAttribute="userTo" class="form-group" method="post">
            <div class="form-group">
                <label class="control-label col-xs-2"><fmt:message key="profile.name"/></label>
                <spring:bind path="name">
                    <input name="${status.expression}" value="${status.value}">
                    <span class="${status.error ? 'alert alert-danger' : '' }" style="padding: 6px">${status.errorMessage}</span>
                </spring:bind>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-2"><fmt:message key="profile.email"/></label>
                <spring:bind path="email">
                    <input type="email" name="${status.expression}" value="${status.value}">
                    <span class="${status.error ? 'alert alert-danger' : '' }" style="padding: 6px">${status.errorMessage}</span>
                </spring:bind>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-2"><fmt:message key="profile.password"/></label>
                <spring:bind path="password">
                    <input type="password" name="${status.expression}" placeholder="Your password...">
                    <span class="${status.error ? 'alert alert-danger' : '' }" style="padding: 6px">${status.errorMessage}</span>
                </spring:bind>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-2"><fmt:message key="profile.calories"/></label>
                <spring:bind path="caloriesPerDay">
                    <input type="number" name="${status.expression}" value="${status.value}" step="10">
                    <span class="${status.error ? 'alert alert-danger' : '' }" style="padding: 6px">${status.errorMessage}</span>
                </spring:bind>
            </div>
            <div class="form-group">
                <div class="col-xs-offset-2">
                    <button type="submit" class="btn btn-success"><fmt:message key="profile.update"/></button>
                </div>
            </div>
        </form:form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</html>
