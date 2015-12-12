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
    <h2>Обратная связь</h2>
    <p>
      Нет сериала, который тебе интересен?
      Вышла новая серия, а мы ее пропустили?
      Есть предложения?
      Пиши
    </p>
    <p>

    </p>
    <form name='loginForm' action="/feedback" method='POST' accept-charset="UTF-8">
      <label>
        От <span id="author"></span>:
        <textarea name="message" style="width: 100%; height: 100px;" placeholder="Текст сообщения"></textarea>
      </label>
      <label id="anonChecbox">
        <input type='checkbox' name='anonymous' onchange="e => {authorField.innerText = e.srcElement.checked ? author : anon}">
        Анонимно
      </label>
      <br>
      <button>Отправить</button>
    </form>
    <h3>${message}</h3>
  </div>
</div>
<script>
  var anon = "Неизвестного Доброжелателя";
  var author = '${email}';
  var authorField = document.getElementById("author");
  if (author) {
    authorField.innerText = author;
  } else {
    document.forms[0].anonymous.checked = true;
    document.getElementById("anonChecbox").style.display = 'none';
    authorField.innerText = anon;
  }
</script>
