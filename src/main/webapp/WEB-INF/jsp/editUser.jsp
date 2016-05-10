<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Редактировать пользователя</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-xs-4 col-md-2"></div>
        <div class="col-xs-10 col-md-8">
            <form method="POST" action="<c:url value="/user/userEditSave"/>">
            <%--@declare id="name"--%><%--@declare id="email"--%><%--@declare id="password"--%><%--@declare id="isadmin"--%><%--@declare id="address"--%><%--@declare id="phone"--%><%--@declare id="country"--%><input
                type="hidden" name="id" value="${user.getId()}"/>
            <label for="name">Имя: </label>
            <input type="text" class="form-control" name="name" value="${user.getName()}"/>
            <br/>
            <label for="email">Email: </label>
            <input type="text" class="form-control" name="email" value="${user.getEmail()}"/>
            <br/>
            <label for="password">Пароль: </label>
            <input type="text" class="form-control" name="password" value="${user.getPassword()}"/>
            <br/>
            <label for="address">Адрес: </label>
            <input type="text" class="form-control" name="address" value="${user.getAddress()}"/>
            <br/>
            <label for="phone">Телефон: </label>
            <input type="text" class="form-control" name="phone" value="${user.getPhone()}"/>
            <br/>
            <label for="country">Страна: </label>
            <input type="text" class="form-control" name="country" value="${user.getCountry()}"/>
            <br/>
            <label for="isAdmin">Сделать его администратором </label>
            <input type="checkbox" name="isAdmin" value="${user.isIsAdmin()}"
                   <c:if test="${user.isAdmin}">checked=""</c:if>/>
            <br/>
            <input type="submit" class="btn btn-success" value="Сохранить"/>

            <a class="btn btn-success" href="<c:url value="/user/show" />">Отмена</a>
            </form>
        </div>
        <div class="col-xs-4 col-md-2"></div>
    </div>
</div>

</body>
</html>