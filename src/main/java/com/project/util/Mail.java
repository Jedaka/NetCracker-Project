package com.project.util;

import com.project.model.Episode;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Юрий on 03.12.2015.
 */
public class Mail {


    private static final String FROM = "notification.netserials@mail.ru";
    private static final String PASSWORD = "netcrackerproject";
    private static final String HOST = "smtp.mail.ru";
    private static final String PORT = "587";
    private static final Properties PROPERTIES = System.getProperties();
    private static final Session SESSION;
    private static final Logger logger = Logger.getLogger(Mail.class);
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
    private final InternetAddress[] to;

    public Mail(String[] to) throws AddressException {
        this.to = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++) {
            this.to[i] = new InternetAddress(to[i]);
        }
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
            logger.info("Notification about new episode of " + title + " has been sent to " + to);

        }catch (MessagingException e){
            logger.warn(e.getMessage());
        }
    }

}
