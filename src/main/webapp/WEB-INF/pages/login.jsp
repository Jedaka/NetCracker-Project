<%--
  Created by IntelliJ IDEA.
  User: jedaka
  Date: 10.11.2015
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form name='loginForm'
      action="/auth/login" method='POST'>

    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='email' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td>Remember me:</td>
            <td><input type='checkbox' name='remember' /></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit"
                                   value="submit" /></td>
        </tr>
    </table>
</form>

</div>
</body>
</html>
