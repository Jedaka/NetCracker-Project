package com.project.util;

import com.project.model.Serial;
import com.project.model.Studio;
import com.project.model.Subscription;
import com.project.model.User;
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

        /* Serials and Studios */

        //Кураж Бамбей

        Serial bigBangTheory = new Serial("Big Bang Theory", "en");
        Serial howIMetYourMother = new Serial("How I Met Your Mother", "en");
        Serial iLiveWithModels = new Serial("I Live With Models", "en");

        Studio kurazhBambey = new Studio("Кураж Бамбей", "ru");

        tokenService.save(bigBangTheory, kurazhBambey);
        tokenService.save(bigBangTheory, null);
        tokenService.save(howIMetYourMother, kurazhBambey);
        tokenService.save(iLiveWithModels, kurazhBambey);

        //LostFilm

        Serial walkingDead = new Serial("Walking Dead", "en");
        Serial gotham = new Serial("Gotham", "en");
        Serial americanHorrorStory = new Serial("American Horror Story", "en");

        Studio lostFilm = new Studio("LostFilm", "ru");

        tokenService.save(walkingDead, lostFilm);
        tokenService.save(gotham, lostFilm);
        tokenService.save(americanHorrorStory, lostFilm);

        //NovaFilm

        Serial supernatural = new Serial("Supernatural", "en");
        Serial dexter = new Serial("Dexter", "en");
        Serial lieToMe = new Serial("Lie To Me", "en");

        Studio novaFilm = new Studio("NovaFilm", "ru");

        tokenService.save(supernatural, novaFilm);
        tokenService.save(dexter, novaFilm);
        tokenService.save(lieToMe, novaFilm);

        /* Users */

        User vasya = new User("vasya@gmail.com", "qwerty");
        vasya.addSubscription(new Subscription(bigBangTheory, kurazhBambey));
        vasya.addSubscription(new Subscription(supernatural, novaFilm));

        User petya = new User("petua@yandex.ru", "sad123");
        petya.addSubscription(new Subscription(walkingDead, lostFilm));

        User stepa = new User("xcks12@yahoo.com", "saczqw421");
        petya.addSubscription(new Subscription(howIMetYourMother, kurazhBambey));

        User katya = new User("kate123@gmail.com", "katekate");

        User natasha = new User("dogcat@mail.ru", "123456");
        natasha.addSubscription(new Subscription(bigBangTheory, kurazhBambey));
        natasha.addSubscription(new Subscription(howIMetYourMother, kurazhBambey));
        natasha.addSubscription(new Subscription(iLiveWithModels, kurazhBambey));
        natasha.addSubscription(new Subscription(walkingDead, lostFilm));
        natasha.addSubscription(new Subscription(dexter, novaFilm));

        userService.save(vasya);
        userService.save(petya);
        userService.save(stepa);
        userService.save(katya);
        userService.save(natasha);

    }
}
