package com.project.mvc;

import com.project.database.dao.UserDAO;
import com.project.entity.Serial;
import com.project.entity.Studio;
import com.project.entity.Subscription;
import com.project.entity.User;

import java.util.Arrays;

/**
 * Created by jedaka on 31.10.2015.
 */
public class App {

    static UserDAO userDAO = new UserDAO();

    public static void main(String[] args) {

        User user = new User();
        user.setEmail("hello@ksd.ru");
        user.setName("Zhek");

        Studio studio1 = new Studio();
        studio1.setName("LostFilm");
        studio1.setLanguage("ru");

        Serial serial = new Serial();
        serial.setTitle("LOST");
        serial.setLanguage("en");

        Subscription subscription = new Subscription();
        subscription.setSerial(serial);
        subscription.setStudio(studio1);
        user.setSubscriptions(Arrays.asList(subscription));

        userDAO.save(user);

        System.out.println(userDAO.getById(1));

    }

}
