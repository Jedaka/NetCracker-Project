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
<meta charset="utf-8" />
<title>NetSerials</title>
<link rel="stylesheet" href="static/css/styles.css" />
<div class='wrapper'>
    <div>
        <a class='logo' href='/'>
            <span class='netcracker__black_color'>Net</span><span class='netcracker__blue_color'>Serials</span>
        </a>
    <span class='profile'>
      <a class='logout' style='color: black;'>

      </a>
    </span>
        <hr/>
    </div>
    <div class='main'>
        <h2>Вход</h2>
        <form name='loginForm' action="/auth" method='POST'>
            <div>
                <label>Email:<br>
                    <input type='email' name='email' required placeholder="inbox@example.com"/>
                </label>
            </div>
            <div>
                <label>Пароль:<br>
                    <input type='password' name='password' required placeholder="******"/>
                </label>
            </div>
            <%= request.getParameter("error") != null ? "<div style='color: red;'>Неверный логин или пароль</div>" : "" %>
            <input type='hidden' name='remember' value="on" />
            <button>Войти</button>
            <a href="/registration" href="logout" class="logout">Забыл пароль?</a>
        </form>
    </div>
</div>
