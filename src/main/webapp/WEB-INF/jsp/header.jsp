<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/css/shopStyle.css"/>" rel="stylesheet">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<link rel="shortcut icon" href="favicon.ico">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<head>

</head>
<body>
<div class="container">
    <div class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand"> LibStore</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Категории<b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/shop/category/1" />">Художественная литература</a></li>
                            <li><a href="<c:url value="/shop/category/2" />">Фантастика</a></li>
                            <li><a href="<c:url value="/shop/category/3" />">Поэзия</a></li>
                            <li><a href="<c:url value="/shop/category/4" />">Постапокалиптика</a></li>
                            <li><a href="<c:url value="/shop/category/5" />">История</a></li>
                            <li><a href="<c:url value="/shop/category/6" />">Культура и искусство</a></li>
                            <li><a href="<c:url value="/shop/category/7" />">Филосовские науки</a></li>
                            <li><a href="<c:url value="/shop/category/8" />">Для школы</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <security:authorize access="isAnonymous()">
                        <li><a href="<c:url  value="/user/login"/>">Войти</a></li>
                        <li><a href="<c:url value="/user/regist" />">Регистрация</a></li>
                    </security:authorize>
                    <security:authorize  access="hasRole('ROLE_USER')">
                        <li><a href="<c:url value="/shop" />">Подробно</a></li>
                        <li><a href="<c:url value="/user/show" />">Профиль</a></li>
                        <li><a href="<c:url value="/shop/history/${thisUser.id}"/>">История заказов</a></li>
                        <li><a href="<c:url value="/user/logout" />">Выход</a></li>
                    </security:authorize>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>