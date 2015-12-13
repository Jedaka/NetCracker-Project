package com.project.mvc;

import com.project.model.User;
import com.project.notification.MailSender;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.SecureRandom;

@Controller
@EnableWebMvc
public class HelloController {

    @Autowired
    UserService userService;
    @Autowired
    MailSender mailSender;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("email", "Hello world!");
        return "hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerRequest(String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            user.setPassword(new BigInteger(40, new SecureRandom()).toString(36));
            userService.update(user);
        } else {
            user = new User();
            user.setEmail(email);
            userService.save(user);
        }
        mailSender.sendPassword(email, user.getPassword());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("email", email);
        return modelAndView;
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
    public String subscriptions(ModelMap model, HttpServletResponse response) {
        User user = userService.getCurrentUser();
        model.addAttribute("email", user.getEmail());
        return "subscriptions";
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public String feedback(ModelMap model, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        try {
            User user = userService.getCurrentUser();
            model.addAttribute("email", user.getEmail());
        } catch (Exception e) {
            model.addAttribute("email", "");
        }
        return "feedback";
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public ModelAndView feedback(String message, String anonymous) {
        ModelAndView modelAndView = new ModelAndView();
        String author = "Anonymous";
        if (anonymous == null) {
            try {
                User user = userService.getCurrentUser();
                author = user.getEmail();
                modelAndView.addObject("email", author);
            } catch (Exception e) {
                modelAndView.addObject("email", "");
            }
        }
        mailSender.sendFeedback(message, author);

        modelAndView.addObject("message", "Ваше сообщение было отправлено администраторам");
        modelAndView.setViewName("feedback");
        return modelAndView;
    }
}