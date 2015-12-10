package com.project.notification;

import com.project.model.Episode;
import com.project.model.Subscription;
import com.project.model.User;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Юрий on 03.12.2015.
 */
public class MailSender {


    private final String FROM = "notification.netserials@mail.ru";
    private final String PASSWORD = "netcrackerproject";
    private final String HOST = "smtp.mail.ru";
    private final String PORT = "587";
    private final String UNSUBSCRIBE_URL = "http://localhost:8080/removeSubscription/?removal=%s";
    private final Properties PROPERTIES = System.getProperties();
    private final Session SESSION;
    private final Logger logger = Logger.getLogger(MailSender.class);

    public MailSender() {
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

    public void send(Subscription subscription, Episode episode) {

        User user = subscription.getUser();
        String recipient = user.getEmail();
        String serialTitle = episode.getSerial().getTitle();
        String removal = subscription.getRemoval();

        try {
            MimeMessage message = new MimeMessage(SESSION);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, recipient);
            message.setSubject("Вышла новая серия", "UTF-8");
            message.setText("Cериал + \"" + serialTitle + "\" " +
                    "S" + episode.getSeasonNumber() +
                    "E" + episode.getEpisodeNumber() +
                    "Link: " + episode.getLink() + "\n" +
                    "Отписаться от сериала: " + String.format(UNSUBSCRIBE_URL, removal));

            Transport.send(message);
            logger.info("Notification about new episode of " + serialTitle + " has been sent to " + recipient);

        } catch (MessagingException e) {
            logger.warn(e.getMessage());
        }
    }

}
