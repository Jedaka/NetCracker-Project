package com.project.notification;

import com.project.model.Episode;
import com.project.model.Subscription;
import com.project.model.User;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Юрий on 03.12.2015.
 */
public class MailSender {


    private final String DOMAIN = "http://localhost:8080/";
    private final String UNSUBSCRIBE_URL = DOMAIN + "removeSubscription/?removal=%s";
    private final String LOGIN_URL = DOMAIN + "login";
    private final Address[] ADMINS = new Address[3];
    private final String FROM = "notification.netserials@mail.ru";
    private final String PASSWORD = "netcrackerproject";
    private final String HOST = "smtp.mail.ru";
    private final String PORT = "587";
    private final Properties PROPERTIES = System.getProperties();
    private final Session SESSION;
    private final Logger logger = Logger.getLogger(MailSender.class);

    public MailSender() {

        try {
            ADMINS[0] = new InternetAddress("jhoweiser@gmail.com");
            ADMINS[1] = new InternetAddress("ganshinv@gmail.com");
            ADMINS[2] = new InternetAddress("maksim_larin@yahoo.com");
        } catch (AddressException e) {
            logger.warn("Exception while init emails: " + e.getMessage());

        }
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

    public void sendEpisode(Subscription subscription, Episode episode) {

        User user = subscription.getUser();
        String recipient = user.getEmail();
        String serialTitle = episode.getSerial().getTitle();
        String removal = subscription.getRemoval();

        try {
            MimeMessage message = new MimeMessage(SESSION);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, recipient);
            message.setSubject(serialTitle + ": вышла новая серия", "UTF-8");
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

    public void sendPassword(String email, String password){
        try {
            MimeMessage message = new MimeMessage(SESSION);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, email);
            message.setSubject("NetSerials.ru: Ваш пароль для входа", "UTF-8");
            message.setText("Спасибо за регистрацию на нашем сайте!\n" +
                    "Ваш пароль: " + password + "\n" +
                    "Ссылка для входа: " + LOGIN_URL);
            Transport.send(message);
            logger.info("Password has been sent to " + email);
        } catch (MessagingException e) {
            logger.warn(e.getMessage());
        }
    }

    public void sendFeedback(String feedback, String author){
        try {
            MimeMessage message = new MimeMessage(SESSION);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, ADMINS);
            message.setSubject("NetSerials.ru: Новый фидбек", "UTF-8");
            message.setText("From: " + author + "\n" + feedback);
            Transport.send(message);
            logger.info("Feedback has been sent to: " + Arrays.toString(ADMINS));
        } catch (MessagingException e) {
            logger.warn(e.getMessage());
        }
    }

}
