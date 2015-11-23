package com.project.mvc;

import com.project.communication.IdRequest;
import com.project.communication.JsonResponse;
import com.project.model.Subscription;
import com.project.model.Token;
import com.project.model.User;
import com.project.service.TokenService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by vganshin on 24.11.15.
 */
//@Secured("ROLE_USER")
@RestController
public class SubscriptionController {
    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/getTokens", method = RequestMethod.GET)
    public JsonResponse getTokens() {
        return new JsonResponse(JsonResponse.Status.OK, tokenService.getAll());
    }

    @RequestMapping(value = "/getSubscriptions", method = RequestMethod.GET)
    public JsonResponse getSubscriptions() {
        User user = userService.getCurrentUser();
        Collection<Subscription> subscriptions = user.getSubscriptions();
        return new JsonResponse(JsonResponse.Status.OK, subscriptions);
    }

    @RequestMapping(value = "/addSubscriptionByTokenId", method = RequestMethod.POST)
    public JsonResponse addSubscriptionByTokenId(@RequestBody IdRequest idRequest) {
        Token token = tokenService.get(idRequest.getId());
        User user = userService.getCurrentUser();
        Subscription subscription = new Subscription(token);
        user.getSubscriptions().add(subscription);
        userService.update(user);
        return new JsonResponse(JsonResponse.Status.OK, subscription);
    }

    @RequestMapping(value = "/removeSubscriptionById", method = RequestMethod.POST)
    public JsonResponse removeSubscriptionById(@RequestBody IdRequest idRequest) {
        Token token = tokenService.get(idRequest.getId());
        User user = userService.getCurrentUser();
        Collection<Subscription> subscriptions = user.getSubscriptions();
        Subscription wow = null;
        for(Subscription subscription : subscriptions) {
            if (subscription.getId() == idRequest.getId()) {
                wow = subscription;
                subscriptions.remove(subscription);
                break;
            }
        }
        userService.update(user);
        return new JsonResponse(JsonResponse.Status.OK, wow);
    }
}
