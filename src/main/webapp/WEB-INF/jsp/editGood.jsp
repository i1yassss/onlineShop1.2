
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактировать товар</title>
    </head>
    <body>
    <%@include file="header.jsp" %>
        <div class="row">
        <div class="col-xs-4 col-md-2"></div>
        <div class="col-xs-10 col-md-8">
            <form method="GET" action="<c:url value="/shop/goodEditSave/${good.getId()}"/>">
            <%--@declare id="name"--%><%--@declare id="desc"--%><%--@declare id="price"--%><%--@declare id="cat"--%><%--@declare id="img"--%><input type="hidden" name="id" value="${good.getId()}" />
            <label for="name">Название:</label>
            <input type="text" class="form-control" name="name" value="${good.getName()}" />
            <label for="desc">Автор:</label>
            <input type="text" class="form-control" name="desc" value="${good.getDescription()}" />
            <label for="price">Цена:</label>
            <input type="text" class="form-control" name="price" value="${good.getPrice()}"/>
            <label for="cat">Категория:</label>
            <select name="cat" class="form-control">
                <c:forEach items="${cats}" var ="cat">
                    <option <c:if test="${good.category_id} == ${cat.id}">selected</c:if>value="${cat.id}">${cat.name}</option>
                </c:forEach>
            </select>
            <label for="img">Название изображения:</label>
            <input type="text" class="form-control" name="img" value="${good.getImage()}"/>
            <input type="submit" class="btn btn-success" value="Сохранить"/>
            <a class="btn btn-success" href="<c:url value="/shop" />">Отмена</a>
        </form>
        </div>
        <div class="col-xs-4 col-md-2"></div>
    </div>

    </body>
</html>
