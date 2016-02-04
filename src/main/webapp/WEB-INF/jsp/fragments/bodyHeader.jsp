<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<header>
    <a href="meals"><div class="navbar-header navbar-brand"><fmt:message key="app.title"/></div></a>
</header>--%>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="meals">
            <div class="navbar-header navbar-brand"><fmt:message key="app.title"/></div>
        </a>

        <div class="collapse navbar-collapse">
            <form class="navbar-form navbar-right">
                <a class="btn btn-info" role="button" href="users"><fmt:message key="app.title"/></a>
                <a class="btn btn-primary" role="button" href=""><fmt:message key="root.login"/></a>
            </form>
        </div>
    </div>
</div>