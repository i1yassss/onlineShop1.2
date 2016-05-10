<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container-fluid">
    <div class="row" style="margin-top:200px;">
        <div class="col-xs-6 col-md-4"></div>
        <div class="col-xs-6 col-md-4">
            <div class="jumbotron" style="text-align: center">
                <form action="/login/process" method="post">
                    Email: <input type="text" name="j_username" align="center" class="form-control"/>
                    Пароль: <input type="password" name="j_password" align="center" class="form-control"/>
                    </br>
                    <div style="text-align: center;">
                        <input type="submit" class="btn btn-default" value="Войти"/>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-xs-6 col-md-4"></div>
    </div>
</div>
</body>
</html>