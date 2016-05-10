<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="http://www.99lime.com/site/templates/js/kickstart.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Категории</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-5">
            <div class="page-header">
            <h1>Просмотр категорий</h1>

            <p class="lead"></p>
        </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title">Категории</h1>
                </div>
                <h1 class="panel-body">
                        <c:forEach items="${cats}" var="cat">
                            <p><a class="btn btn-default btn-lg btn-block" href="<c:url value="/shop/category/${cat.id}" />"> ${cat.name}</a></p>
                        </c:forEach>
                </h1>

                <c:if test="${current.isAdmin}">
                    <br/>

                    <div><a class="btn btn-success" href="<c:url value="/admin/add-category" />">Добавить категорию</a>
                    </div>
                </c:if>

            </div>
        </div>
        <div class="col-md-5">
            <div id="templatemo_menu" class="ddsmoothmenu">
                <div class="page-header">
                    <h1>Тележка с товарами</h1>

                    <p class="lead"></p>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h1 class="panel-title">Корзина</h1>
                    </div>
                    <div class="panel-body">
                        <ul class="jumbotron">
                            <c:forEach items="${basket}" var="item">`
                                <ol>${item.value.good.name} Количество:
                                    <span>${item.value.count}</span>
                                    <a style="color: red"
                                       href="<c:url value="/shop/deleteGoodInBasket/${item.value.good.category_id}/${item.value.good.id}" />">
                                        Удалить товар</a></ol>
                            </c:forEach>
                        </ul>
                        <div><a class="btn btn-success" href="<c:url value="/shop/orderAdd" />">Заказать</a>
                            <a class="btn btn-primary" href="<c:url value="/shop/history/${thisUser.id}"/>">История заказов</a>
                            <a class="btn btn-danger" href="<c:url value="/shop/basketDelete"/>">Очистить</a></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>
</body>
</html>
