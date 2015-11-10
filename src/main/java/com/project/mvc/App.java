package com.project.mvc;

import com.project.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by jedaka on 31.10.2015.
 */
public class App {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("C:\\Users\\jedaka\\Desktop\\Project\\src\\main\\webapp\\WEB-INF\\applicationContext.xml");

        UserService userService = applicationContext.getBean("userService", UserService.class);

//        User user = new User();
//        user.setEmail("hello@ksd.ru");
//        user.setPassword("sad");
//
//        Studio studio1 = new Studio();
//        studio1.setName("LostFilm");
//        studio1.setLanguage("ru");
//
//        Serial serial = new Serial();
//        serial.setTitle("LOST");
//        serial.setLanguage("en");
//
//        Subscription subscription = new Subscription();
//        subscription.setSerial(serial);
//        subscription.setStudio(studio1);
//        user.setSubscriptions(Arrays.asList(subscription));
//        userDAO.save(user);

        System.out.println(userService.findByEmail("hello@ksd.ru"));

    }

}
