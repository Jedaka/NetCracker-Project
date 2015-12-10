package com.project;

import com.project.service.ApplicationContextProvider;
import com.project.service.SubscriptionService;
import com.project.service.TokenService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by jedaka on 10.12.2015.
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("C:\\Users\\jedaka\\Desktop\\Project\\src\\main\\webapp\\WEB-INF\\applicationContext.xml");
        SubscriptionService subscriptionService = ApplicationContextProvider.getApplicationContext().getBean("subscriptionService", SubscriptionService.class);
        TokenService tokenService = ApplicationContextProvider.getApplicationContext().getBean("tokenService", TokenService.class);
        System.out.println(subscriptionService.findSubscriptionsByToken(tokenService.get(1)));
    }

}
