package com.project.mvc;

import com.project.model.User;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@EnableWebMvc
public class HelloController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		return "login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String register(){
		return "register";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
	public String subscriptions(ModelMap model, HttpServletResponse response) {
//		response.setCharacterEncoding("utf-8");
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
}