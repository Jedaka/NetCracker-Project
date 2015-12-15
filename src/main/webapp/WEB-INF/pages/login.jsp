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
            <table width="100%" cellspacing="0" cellpadding="4">
                <tbody>
                    <tr>
                        <td align="right" width="100">
                            Email
                        </td>
                        <td>
                            <input type='email' name='email' required placeholder="inbox@example.com"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            Пароль
                        </td>
                        <td>
                            <input type='password' name='password' required placeholder="password"/>
                        </td>
                    </tr>
                    <%= request.getParameter("error") != null ? "<tr><td></td><td><div style='color: red;'>Неверный логин или пароль</div></td></tr>" : "" %>
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" value="Войти">
                            <a href="/registration" class="logout">Забыли пароль?</a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type='hidden' name='remember' value="on" />
        </form>
    </div>
</div>
