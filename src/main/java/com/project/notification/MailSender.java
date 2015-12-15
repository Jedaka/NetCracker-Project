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
    private final Address[] ADMINS = new Address[1];
    private final String FROM = "notification.netserials@mail.ru";
    private final String PASSWORD = "netcrackerproject";
    private final String HOST = "smtp.mail.ru";
    private final String PORT = "587";
    private final Properties PROPERTIES = System.getProperties();
    private final Session SESSION;
    private final Logger logger = Logger.getLogger(MailSender.class);

    public MailSender() {

        try {
            ADMINS[0] = new InternetAddress("ganshinv@gmail.com");
//            ADMINS[0] = new InternetAddress("jhoweiser@gmail.com");
//            ADMINS[2] = new InternetAddress("maksim_larin@yahoo.com");
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
        /**
         * !!! Еще не сделал
         *
         * Тема: [NetSerials] {SerialName} ({StudioName})
         *
         * Дорогой подписчик,
         *
         * вышла [{EpisodeNumber} серия {SeasonNumber}-го сезона].
         * Если Вам больше не интересен данный сериал, [можете отписаться от него.]
         *
         * Приятного просмотра,
         * команда NetSerials.
         */
        User user = subscription.getUser();
        String recipient = user.getEmail();

        String serialTitle = episode.getSerial().getTitle();
        String studioName = episode.getStudio().getName();

        int seasonNumber = episode.getSeasonNumber();
        int episodeNumber = episode.getEpisodeNumber();
        String link = episode.getLink();

        String removal = subscription.getRemoval();
        String unsubscribeLink = String.format(UNSUBSCRIBE_URL, removal);

        try {
            MimeMessage message = new MimeMessage(SESSION);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, recipient);
            message.setHeader("Content-Type", "text/html; charset=UTF-8");
            message.setSubject("[NetSerials] " + serialTitle + " (" + studioName + ")", "UTF-8");

            String text = "Дорогой подписчик,\n\nвышла %1$s-я серия %2$s-го сезона. (%3$s)\n\nЕсли Вам больше не интересен данный сериал, можете отписаться от него. (%4$s)\n\nПриятного просмотра,\nкоманда NetSerials.";

            message.setText(String.format(text, seasonNumber, episodeNumber, link, unsubscribeLink));

            Transport.send(message);
            logger.info("Notification about new episode of " + serialTitle + " has been sent to " + recipient);

        } catch (MessagingException e) {
            logger.warn(e.getMessage());
        }
    }

    public void sendPassword(String email, String password) {
        /**
         * Тема: Регистрация на сайте NetSerials
         *
         * Благодарим за регистрацию!
         *
         * Ваш пароль для входа: {password}
         *
         * Команда NetSerials.
         */
        try {
            MimeMessage message = new MimeMessage(SESSION);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, email);
            message.setSubject("Регистрация на сайте NetSerials", "UTF-8");
            message.setText("Благодарим за регистрацию!" +
                    "\n\nВаш пароль для входа: " + password +
                    "\n\nКоманда NetSerials.");
            Transport.send(message);
            logger.info("Password has been sent to " + email);
        } catch (MessagingException e) {
            logger.warn(e.getMessage());
        }
    }

    public void sendFeedback(String email, String theme, String text) {
        /**
         * Тема: [NetSerials] {theme}
         *
         * От {email}:
         * {message}
         */
        try {
            MimeMessage message = new MimeMessage(SESSION);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO, ADMINS);
            message.setSubject("[NetSerials] " + theme, "UTF-8");
            message.setText("От " + email + ":\n" + text);
            Transport.send(message);
            logger.info("Feedback has been sent to: " + Arrays.toString(ADMINS));
        } catch (MessagingException e) {
            logger.warn(e.getMessage());
        }
    }

}
