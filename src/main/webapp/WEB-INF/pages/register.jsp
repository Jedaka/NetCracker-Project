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
        <h2>Регистрация</h2>
        <p>Авторизируем по следующей схеме: на почту, которую ты укажешь, будет выслан пароль для входа.</p>
        <p>Забыл пароль? Регистрируйся снова. Пароль вышлем новый. Подписки сохраним.</p>
        <form name='loginForm' action="/registration" method='POST'>
            <label>Email:
                <input type='email' name='email'>
            </label>
            <button>Зарегистрировать</button>
        </form>

        <h3>${message}</h3>

    </div>
</div>
