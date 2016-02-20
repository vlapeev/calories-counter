<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><fmt:message key="app.title"/></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form:form class="navbar-form navbar-right" action="forms/login" method="post">
                        <div class="form-group">
                            <input type="text" placeholder="Email" class="form-control" name="username">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="Password" class="form-control" name="password">
                        </div>
                        <button type="submit" class="btn btn-success"><fmt:message key="app.login"/></button>
                    </form:form>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${pageContext.response.locale}<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a onclick="show('en')">English</a></li>
                        <li><a onclick="show('ru')">Русский</a></li>
                    </ul>
                    <script type="text/javascript">
                        function show(lang) {
                            window.location.href = window.location.href.split('?')[0] + '?lang=' + lang;
                        }
                    </script>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="jumbotron">
    <div class="container">
        <c:if test="${error}">
            <div class="alert alert-danger">
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="alert alert-success">
                <fmt:message key="${message}"/>
            </div>
        </c:if>

        <p>User login: <b>user@yandex.ru / password</b></p>

        <p>Admin login: <b>admin@gmail.com / admin</b></p>

        <a class="btn btn-primary btn-lg" href="register">Register »</a>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</html>