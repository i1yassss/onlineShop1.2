<%@page import="ru.kpfu.itis.model.Order" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Заказать</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <div class="row">
        <form method="POST" action="<c:url value="/shop/saveOrder"/>">
            <div class="col-md-1"></div>
            <div class="col-md-5">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Заказ</h3>
                    </div>
                    <div id="templatemo_menu" class="ddsmoothmenu">
                        <ul sidebar_list>
                            <input type="hidden" name="id" value="${thisUser.id}"/>

                            <div>
                                <%--@declare id="name"--%>
                                <label for="name">Имя: </label>
                                <input type="text" class="form-control" name="userName" value="${current.name}"
                                       readonly="readonly"/>
                            </div>
                            <div>
                                <label for="name">Адрес: </label>
                                <input type="text" class="form-control" name="DeliverAddress" value=""/>
                            </div>
                        </ul>
                        <input type="submit" class="btn btn-success" value="Заказать"/>
                    </div>
                </div>
            </div>

            <div class="col-md-5">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Вы собираетесь заказать</h3>
                    </div>
                    <div class="panel-body">
                        <ul>
                            <c:forEach items="${basket}" var="item">
                                <li>Название: ${item.value.good.name}<br/>
                                    Количество: ${item.value.count}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Последовательность заполнения</h3>
                    </div>
                    <address>
                        <h3><strong>Имя Фамилия покупателя</strong><br></h3>
                        <h3>Улица, дом, квартира<br></h3>
                        <h3>Город, Индекс, Страна<br></h3>
                          Контактный телефон<br>
                        <h3><abbr title="Phone">  P:</abbr> (123) 456-7890</h3>
                    </address>
                </div>
            </div>
            <div class="col-md-1"></div>
        </form>
    </div>
</div>
</body>
</html>
