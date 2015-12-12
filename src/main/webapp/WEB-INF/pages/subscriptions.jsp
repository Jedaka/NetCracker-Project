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
    <p>NetSerials -- это простой способ следить за вашими любимыми сериалами без нервного мониторинга сайтов студий или других побочных ресурсов</p>
    <div id="react-app"></div>
  </div>

  <script src="/static/js/bundle.js"></script>
</div>
