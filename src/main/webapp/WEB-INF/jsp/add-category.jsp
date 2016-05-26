<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>

<!DOCTYPE html>
<html>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-4 col-md-2"></div>
        <div class="col-xs-10 col-md-8">
            <form class="form-horizontal" role="form" action="/admin/add-category" method="post">
                <h2 class="form-signin-heading">Добавить категорию</h2>

                <div class="form-group">
                    <div class="col-md-5">
                        <input type="text" name="name" class="form-control" placeholder="Имя категории" required
                               autofocus>
                    </div>
                </div>
                <div class="col-md-5">
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Добавить</button>
                </div>
            </form>

        </div>

        <div class="col-xs-4 col-md-2"></div>

</body>
</html>