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
    <div class="feedback__comment">
      <p>Нет сериала, который Вам интересен?</p>
      <p>Вышла новая серия, а мы ее пропустили?</p>
      <p>Есть предложения?</p>
      <p>Напишите нам.</p>
    </div>
    <form name='loginForm' action="/feedback" method='POST' accept-charset="UTF-8">
      <table width="100%" cellspacing="0" cellpadding="4">
        <tbody><tr>
          <td align="right" width="100">От</td>
          <td>
            <input type="email" name="email" placeholder="inbox@example.com">
          </td>
        </tr>
        <tr>
          <td align="right">Тема<span style="color: red;">*</span></td>
          <td>
            <select name="theme" required>
              <option value="error" selected>Ошибка</option>
              <option value="feedback">Отзыв</option>
              <option value="suggestion">Предложение</option>
            </select>
          </td>
        </tr>
        <tr>
          <td align="right" valign="top">Сообщение<span style="color: red;">*</span></td>
          <td>
            <textarea name="message" cols="35" rows="10" placeholder="" required></textarea>
          </td>
        </tr>
        <tr>
          <td></td>
          <td>
            <input type="submit" value="Отправить">
          </td>
        </tr>
        </tbody>
      </table>
    </form>
    <p><span style="color:red;">*</span> — обязательные поля. Если вы не укажите адрес для обратной связи, мы не сможем Вам ответить.</p>
    <h3>${message}</h3>
  </div>
</div>

