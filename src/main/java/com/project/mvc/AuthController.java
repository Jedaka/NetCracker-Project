package com.project.mvc;

import com.project.model.User;
import com.project.service.ApplicationContextProvider;
import com.project.service.UserService;
import com.project.some.ChangePasswordForm;
import com.project.some.RegistrationForm;
import com.project.some.jsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    public jsonResponse register(RegistrationForm form) {
        int id;
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        try {
            id = userService.save(user);
            user = userService.read(id);
        } catch (Exception e) {
            return new jsonResponse(jsonResponse.Status.ERROR, "Already exist");
        }
        return new jsonResponse(jsonResponse.Status.OK, "Good");
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public jsonResponse changePassword(ChangePasswordForm passwordForm) {

        String message = userService.changePassword(passwordForm) ? "Good" : "Bad";

        return new jsonResponse(jsonResponse.Status.OK, message);
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public jsonResponse profile() {
        User user = userService.getCurrentUser();
        return new jsonResponse(jsonResponse.Status.OK, user);
    }

}
