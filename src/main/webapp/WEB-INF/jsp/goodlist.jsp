<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список товаров</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-default" style="margin-top: 60px">
                <div class="panel-heading">
                    <h1 class="panel-title">Выбор товаров</h1>
                </div>
                <div class="panel-body">
                    <ul>
                        <c:forEach items="${goods}" var="good">
                            <table cellspacing="0"><tr>
                            <td><img src="/img/${good.image}" class="img-rounded" style="width:200px; height:320px;"></td>
                            <td> </td>
                                <td><strong>${good.name}</strong><br/>
                                Автор: <strong>${good.description}</strong></br>
                                Цена товара: <span style="color: red;">${good.price} Рублей
                                    </span>
                                <br/>
                                <a class="btn btn-success" href="<c:url value="/shop/add/${good.category_id}/${good.id}" />">Добавить в корзину</a>
                                <c:if test="${current.isAdmin}"> <a class="btn btn-info" href="<c:url value="/shop/editGood/${good.category_id}/${good.id}" />">Редактировать</a>
                                    <a class="btn btn-danger" href="<c:url value="/shop/delete/${good.category_id}/${good.id}" />">Удалить</a>
                                </c:if>
                            </td>
                            </li>
                            </tr></table>
                            <br/>
                        </c:forEach>
                    </ul>
                    <c:if test="${current.isAdmin}">
                        <br/>

                        <div><a class="btn btn-success" href="<c:url value="/shop/addGood" />">Добавить товар</a>
                        </div>
                    </c:if>

                </div>
            </div>
        </div>

        <div class="col-md-1"></div>
        <div class="col-md-5">
            <div class="panel panel-default" style="margin-top: 60px">
                <div class="panel-heading">
                    <h1 class="panel-title">Корзина</h1>
                </div>
                <div class="panel-body">
                    <ul class="jumbotron">
                        <c:forEach items="${basket}" var="item">
                            <ol>${item.value.good.name} Количество:
                                <span>${item.value.count}</span>
                                <a style="color: red"
                                   href="<c:url value="/shop/deleteGoodInBasket/${item.value.good.category_id}/${item.value.good.id}" />">
                                    Удалить товар</a></ol>
                        </c:forEach>
                    </ul>

                    <sec:authorize access="hasRole('ROLE_USER')">
                    <div><a class="btn btn-success" href="<c:url value="/shop/orderAdd" />">Заказать</a>
                        <a class="btn btn-primary" href="<c:url value="/shop/history/${thisUser.id}"/>">История
                            заказов</a>
                        <a class="btn btn-danger" href="<c:url value="/shop/basketDelete"/>">Очистить</a>
                    </div>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
