package com.project.util;

import com.project.model.Episode;
import com.project.model.Token;
import com.project.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Created by Юрий on 03.12.2015.
 */
public class Mail {

    @Autowired
    private TokenService tokenService;

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
    public void send(Episode episode, String title){
        try{
            MimeMessage message = new MimeMessage(SESSION);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject("Вышла новая серия", "UTF-8");
            message.setText("Cериал + \"" + title + "\" " +
                    "S" + episode.getSeasonNumber() +
                    "E" + episode.getEpisodeNumber()+
                    "Link: " + episode.getLink()
            );
            Transport.send(message);
            System.out.println("Отправлено!");
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

}
