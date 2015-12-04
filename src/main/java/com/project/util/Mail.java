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
    private static final String PASSWORD = "netcrackerproject";
    private static final String HOST = "smtp.mail.ru";
    private static final String PORT = "587";
    private static final Properties PROPERTIES = System.getProperties();
    private static final Session SESSION;
    static {
        PROPERTIES.put("mail.smtp.host", HOST);
        PROPERTIES.put("mail.smtp.starttls.enable", "true");
        PROPERTIES.put("mail.smtp.auth", "true");
        PROPERTIES.put("mail.smtp.port", PORT);
        SESSION = Session.getInstance(PROPERTIES, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        });
    }
    private final String to;

    public Mail(String to){
        this.to = to;
    }
    public void send(Episode episode, String title){
        try{
            MimeMessage message = new MimeMessage(SESSION);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Вышла новая серия", "UTF-8");
            message.setText("Cериал + \"" + title + "\" " +
                    "S" + episode.getSeasonNumber() +
                    "E" + episode.getEpisodeNumber()+
                    "Link: " + episode.getLink()
            );
            Transport.send(message);
            System.out.println("Notification has been sent!");
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        Mail mail = new Mail("maksim_larin@mail.ru");
//        Episode episode = new Episode();
//        episode.setId(1);
//        episode.setEpisodeNumber(1);
//        episode.setSeasonNumber(2);
//        episode.setLink("www.mail.ru");
//        mail.send(episode, "test");
//    }

}
