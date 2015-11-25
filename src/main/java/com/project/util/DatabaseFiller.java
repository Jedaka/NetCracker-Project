package com.project.util;

import com.project.model.*;
import com.project.service.ApplicationContextProvider;
import com.project.service.TokenService;
import com.project.service.UserService;
import org.springframework.context.ApplicationContext;

/**
 * Created by jedaka on 12.11.2015.
 */
public class DatabaseFiller {

    public static void persistTestData() {

        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        TokenService tokenService = applicationContext.getBean("tokenService", TokenService.class);
        UserService userService = applicationContext.getBean("userService", UserService.class);

        //LostFilm

        Serial walkingDead = new Serial("Walking Dead", "en");
        Serial gotham = new Serial("Gotham", "en");
        Serial americanHorrorStory = new Serial("American Horror Story", "en");

        Studio lostFilm = new Studio("LostFilm", "ru");

        Token token1 = new Token(walkingDead, lostFilm);
        tokenService.save(token1);
        Token token2 = new Token(gotham, lostFilm);
        tokenService.save(token2);
        Token token3 = new Token(americanHorrorStory, lostFilm);
        tokenService.save(token3);

        User user = new User();
        user.setEmail("vasya@gmail.com");
        user.setPassword("qwerty");
        Subscription subscription = new Subscription();
        subscription.setToken(token1);
        user.addSubscription(subscription);

        userService.save(user);

    }
}
