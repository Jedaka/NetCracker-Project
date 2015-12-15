<%--
  Created by IntelliJ IDEA.
  User: jedaka
  Date: 10.11.2015
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<link rel="shortcut icon" type="image/x-icon" href="static/favicon.ico">
<meta charset="utf-8"/>
<title>NetSerials</title>
<link rel="stylesheet" href="static/css/styles.css"/>
<div class='wrapper'>
    <div>
        <a class='logo' href='/'>
            <span class='netcracker__black_color'>Net</span><span class='netcracker__blue_color'>Serials</span>
        </a>
    <span class='profile'>
        <a href="/subscriptions" class='logout'>
            Вход
        </a>
    </span>
        <hr/>
    </div>
    <div class='main'>

        <% String email = request.getParameter("email"); %>
        <% if (email != null) {%>
        <h2>Благодарим за регистрацию!</h2>
        <p>
            Пароль был отправлен на адрес <b><%= email %></b>, проверьте почту!
        </p>
        <% } else { %>
        <h2>Регистрация</h2>

        <p>Авторизируем по следующей схеме: на почту, которую Вы укажете, будет выслан пароль для входа.</p>
        <p><b>Забыли пароль?</b> Регистрируйтесь снова. Пароль вышлем новый. Подписки сохраним.</p>
        <form name='loginForm' action="/registration" method='POST'>
            <label>Email
                <input type='email' name='email' required placeholder="inbox@example.com"/>
            </label>
            <button>Зарегистрировать</button>
        </form>
        <% } %>
    </div>
</div>
