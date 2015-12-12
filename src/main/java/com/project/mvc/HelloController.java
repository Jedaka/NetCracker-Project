package com.project.mvc;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@EnableWebMvc
public class HelloController {

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
	public String subscriptions(HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		return "subscriptions";
	}

}