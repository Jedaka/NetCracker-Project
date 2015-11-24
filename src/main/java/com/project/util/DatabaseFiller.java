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

        Serial bigBangTheory = new Serial("Big Bang Theory", "en");
        Studio kurazhBambey = new Studio("Кураж Бамбей", "ru");
        Token token1 = new Token(bigBangTheory, kurazhBambey);
        Token token2 = new Token(bigBangTheory, null);
        tokenService.save(token1);
        tokenService.save(token2);

        User user = new User();
        user.setEmail("vasya@gmail.com");
        user.setPassword("qwerty");
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setToken(token1);
        user.addSubscription(subscription);

        userService.save(user);

        user.getSubscriptions().remove(subscription);
        Subscription subscription2 = new Subscription();
        subscription2.setToken(token2);
        subscription2.setUser(user);
        user.addSubscription(subscription2);

        userService.update(user);


        System.out.println(userService.findByEmail("vasya@gmail.com"));

        /* Serials and Studios */

        //Кураж Бамбей


//        tokenService.save(new Token(howIMetYourMother, kurazhBambey));
//        tokenService.save(new Token(iLiveWithModels, kurazhBambey));

        //LostFilm

        Serial walkingDead = new Serial("Walking Dead", "en");
        Serial gotham = new Serial("Gotham", "en");
        Serial americanHorrorStory = new Serial("American Horror Story", "en");

        Studio lostFilm = new Studio("LostFilm", "ru");

        Token token3 = new Token(walkingDead, lostFilm);

        tokenService.save(token3);
        tokenService.save(new Token(gotham, lostFilm));
        tokenService.save(new Token(americanHorrorStory, lostFilm));

        //NovaFilm

        Serial supernatural = new Serial("Supernatural", "en");
        Serial dexter = new Serial("Dexter", "en");
        Serial lieToMe = new Serial("Lie To Me", "en");

        Studio novaFilm = new Studio("NovaFilm", "ru");

        tokenService.save(new Token(supernatural, novaFilm));
        tokenService.save(new Token(dexter, novaFilm));
        tokenService.save(new Token(lieToMe, novaFilm));

        /* Users */

//        User vasya = new User("vasya@gmail.com", "qwerty");
//        vasya.addSubscription(new Subscription(token1));
//        vasya.addSubscription(new Subscription(token2));

//        User petya = new User("petua@yandex.ru", "sad123");
//        petya.addSubscription(new Subscription(token1));
//
//        User stepa = new User("xcks12@yahoo.com", "saczqw421");
//        petya.addSubscription(new Subscription(howIMetYourMother, kurazhBambey));
//
//        User katya = new User("kate123@gmail.com", "katekate");
//
//        User natasha = new User("dogcat@mail.ru", "123456");
//        natasha.addSubscription(new Subscription(bigBangTheory, kurazhBambey));
//        natasha.addSubscription(new Subscription(howIMetYourMother, kurazhBambey));
//        natasha.addSubscription(new Subscription(iLiveWithModels, kurazhBambey));
//        natasha.addSubscription(new Subscription(walkingDead, lostFilm));
//        natasha.addSubscription(new Subscription(dexter, novaFilm));

//        userService.save(vasya);
//        userService.save(petya);
//        userService.save(stepa);
//        userService.save(katya);
//        userService.save(natasha);

    }
}
