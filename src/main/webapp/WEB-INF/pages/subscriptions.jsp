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
      ${email}
      <a href="/logout" class='logout'>
        Выход
      </a>
    </span>
    <hr/>
  </div>
  <div class='main'>
    <p>Выбирайте сериалы, которые Вам интересны. Когда выйдет новая серия, мы Вас проинформируем по электронной почте.</p>
    <p>Если что не так,
      <a href="/feedback" class="logout">напишите нам.</a>
    </p>
    <div id="react-app"></div>
  </div>

  <script src="/static/js/bundle.js"></script>
</div>
