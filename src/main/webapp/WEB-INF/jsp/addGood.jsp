<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Добавить товар</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1>Добавить товар</h1>
<div class="container">
<div class="row">
    <div class="col-xs-4 col-md-2"></div>
    <div class="col-xs-10 col-md-8">
        <form method="GET" action="<c:url value="/shop/goodAddSave"/>">
            <%--@declare id="cat"--%><%--@declare id="name"--%><%--@declare id="desc"--%><%--@declare id="price"--%>
            <%--@declare id="img"--%>
            <input type="hidden" class="form-control" name="id"/>
            <label for="name">Название:</label>
            <input type="text" class="form-control" name="name"/><br/><br/>
            <label for="desc">Автор:</label>
            <input type="text" class="form-control" name="desc"/><br/><br/>
            <label for="price">Цена:</label>
            <input type="text" class="form-control" name="price"/><br/><br/>
            <la>
                <label for="cat">Категория:</label>
                <select name="cat" class="form-control">
                    <c:forEach items="${cats}" var="cat">
                        <option value="${cat.id}">${cat.name}</option>
                    </c:forEach>
                </select><br/>
            </la>
                <label for="img">Название картинки:</label>
                <input type="text" class="form-control" name="img"/><br/><br/>
                <input type="submit" class="btn btn-success" value="Добавить"/>
                <a class="btn btn-success" href="<c:url value="/shop" />">Отмена</a>
        </form>
    </div>
    <div class="col-xs-4 col-md-2"></div>
</div>
</div>
</body>
</html>
