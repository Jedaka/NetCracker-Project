package com.project.mvc;

import com.project.communication.JsonResponse;
import com.project.model.Token;
import com.project.service.TokenService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vganshin on 24.11.15.
 */
@Secured("ROLE_USER")
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
}
