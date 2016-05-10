<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Пользователь</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <div class="jumbotron">
        <h2>Профиль пользователя ${current.name}</h2>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <c:if test="${user1.isAdmin == true}">
                        <h3 class="panel-title">Список пользователей</h3>
                    </c:if>
                </div>
                <div class="panel-body" style="background-color: activeborder">
                    <ul>
                        <c:if test="${current.isAdmin}">
                            <c:forEach items="${users}" var="user1">
                                <li>Имя: ${user1.name}<br/>
                                    Email: ${user1.email}<br/>
                                    Пароль: ${user1.password}<br/>
                                    <c:if test="${user1.isAdmin == true}">Администратор</c:if>
                                    <c:if test="${user1.isAdmin != true}">Пользователь</c:if><br/>
                                    <a class="btn btn-info" href="<c:url value="/user/editUser/${user1.id}" />">Редактировать
                                        пользователя</a>
                                    <a class="btn btn-danger" href="<c:url value="/user/delete/${user1.id}" />">Удалить
                                        пользователя</a></li>
                            </c:forEach>
                            <br/>

                            <div><a class="btn btn-success" href="<c:url value="/user/addUser" />">Добавить
                                пользователя</a></div>
                        </c:if>
                    </ul>
                    <div style="color: red">
                        <c:if test="${!current.isAdmin}">
                            <div>Доброго времени суток, ${current.name}</div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Текущий пользователь</h3>
                </div>
                <div class="panel-body" style="background-color: activeborder">
                    <div>Ваше Имя: ${current.name}</div>
                    <div>Email: ${current.email}</div>
                    <div><c:if test="${current.isAdmin == true}">Администратор</c:if><c:if
                            test="${current.isAdmin != true}">Пользователь</c:if></div>
                    <div>Страна: ${current.country}</div>
                    <div>Ваш адрес: ${current.address}</div>
                    <div>Контактный телефон: ${current.phone}</div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
