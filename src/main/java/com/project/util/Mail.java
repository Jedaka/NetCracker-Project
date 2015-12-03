package com.project.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Created by Юрий on 03.12.2015.
 */
public class Mail {

    private static final String FROM = "notification.netserials@mail.ru";
    private static final String HOST = "localhost";
    private static final Properties PROPERTIES = System.getProperties();
    private static final Session SESSION;
    static {
        PROPERTIES.setProperty("mail.smtp.host", HOST);
        SESSION = Session.getDefaultInstance(PROPERTIES);
    }
    private final String to;

    public Mail(String to){
        this.to = to;
    }
    public void send(){
        try{
            MimeMessage message = new MimeMessage(SESSION);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject("Вышла новая серия", "UTF-8");
            message.setText("Добрый день!\nПо оформленной Вами подписке на сайте ... уведомляем, что вышла новая серия ");
            Transport.send(message);
            System.out.println("Отправлено!");
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

}
