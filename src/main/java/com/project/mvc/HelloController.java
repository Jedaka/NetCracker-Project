package com.project.mvc;

import com.project.entity.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@RequestMapping("/")
public class HelloController {

	@RequestMapping(name = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello");
		return "hello";
	}

	@RequestMapping(value = "/JSON", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getUserInJSON(){
		User user = new User();
		user.setId(1);
		user.setEmail("sd@sad.ru");
		user.setName("sdds");
		return user;
	}

}