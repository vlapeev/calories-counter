<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="scr" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
            <a class="navbar-brand" href="meals"><fmt:message key="app.title"/></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form:form class="navbar-form " method="post" action="logout">
                        <scr:authorize access="isAuthenticated()">
                            <scr:authorize access="hasRole('ROLE_ADMIN')">
                                <a class="btn btn-info" role="button" href="users"><fmt:message key="app.userList"/></a>
                            </scr:authorize>
                            <a class="btn btn-info" role="button" href="profile">${userTo.name} profile</a>
                            <input type="submit" class="btn btn-primary" value="<fmt:message key="app.logout"/>">
                        </scr:authorize>
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