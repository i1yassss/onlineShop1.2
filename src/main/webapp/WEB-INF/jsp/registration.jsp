<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://jakarta.apache.org/struts/tags-html" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Регистрация</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-xs-6 col-md-4"></div>
        <div class="col-xs-6 col-md-4">
            <form method="POST" action="<c:url value="/user/regist"/>" th:th:action="@{/}" th:object="${model}}">
                <%--@declare id="email"--%><%--@declare id="name"--%><%--@declare id="password"--%><%--@declare id="isadmin"--%>
                <%--@declare id="address"--%><%--@declare id="phone"--%><%--@declare id="country"--%>
                <label for="name">Имя:</label>
                <input type="text" class="form-control" name="name"/>
                <label for="email">Email:</label>
                <input type="text" class="form-control" name="email"/>
                <label for="password">Пароль:</label>
                <input type="password" class="form-control" name="password"/>
                <input type="hidden" name="isAdmin" value="0"/>
                <label for="country">Страна:</label>
                <input type="text" class="form-control" name="country"/>
                <label for="address">Адрес:</label>
                <input type="text" class="form-control" name="address"/>
                <label for="phone">Телефон:</label>
                <input type="text" class="form-control" name="phone"/>
                    </br>
                <input type="submit" class="btn btn-success" value="Зарегестрироваться"/>
            </form>
        </div>
        <div class="col-xs-6 col-md-4"></div>
    </div>
</div>

</body>
</html>
