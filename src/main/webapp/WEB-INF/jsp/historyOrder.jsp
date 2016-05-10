<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>История заказов</title>
</head>
<body>
<%@include file="header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="jumbotron">
            <h1>
                <div>Ваши заказы, ${thisUser.name}</div>
            </h1>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <c:if test="${Order.size() != 0}">
            <div class="jumbotron">
            <div class="table-responsive">
                <table class="table">
                    <c:set var="string" scope="session" value="${str}"/>
                    <div id="templatemo_menu" class="container">
                        <c:forEach items="${Order}" var="oi">
                        <c:choose>
                        <c:when test="${string == oi.address}">
                        </c:when>
                        <c:otherwise>

                        <tr  class="active">
                            <th>Адрес</th>
                            <td class="active">${oi.address}</td>
                            <c:set var="string" scope="session" value="${oi.address}"/>
                            </c:otherwise>
                            </c:choose>
                            <c:forEach items="${Good}" var="g">
                            <c:if test="${oi.idGood == g.id}">
                            <th>Товар</th>
                            <td class="active">${g.name}</td>
                            <th>Колиество</th>
                            <td class="active">${oi.amount}</td>
                            <th>Цена</th>
                            <td class="active">${oi.price}</td>
                        </tr>
                            <c:set var="res" scope="session" value="${oi.amount * oi.price}"/>
                        </c:if>
                        </c:forEach>
                        </c:forEach>
                        </c:if>
                </table>
                </div>
                </br>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <c:if test="${Order.size() == 0}"><h2>История пуста</h2></c:if></div>
    <div class="col-md-4"></div>
</div>
</div>
</body>
</html>
