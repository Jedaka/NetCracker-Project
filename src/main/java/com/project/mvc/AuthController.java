package com.project.mvc;

import com.project.model.User;
import com.project.service.UserService;
import com.project.communication.ChangePasswordRequest;
import com.project.communication.RegistrationRequest;
import com.project.communication.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * Created by vganshin on 09.11.15.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public JsonResponse register(@RequestBody RegistrationRequest form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());

        // TODO: We need more exceptions
        try {
            userService.save(user);
        } catch (Exception e) {
            return new JsonResponse(JsonResponse.Status.ERROR, "Already exist");
        }
        return new JsonResponse(JsonResponse.Status.OK, "Good job");
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public JsonResponse changePassword(@RequestBody ChangePasswordRequest passwordForm) {

        String message = userService.changePassword(passwordForm) ? "Good" : "Bad";

        return new JsonResponse(JsonResponse.Status.OK, message);
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public JsonResponse profile() {
        User user = userService.getCurrentUser();
        return new JsonResponse(JsonResponse.Status.OK, user);
    }
}
