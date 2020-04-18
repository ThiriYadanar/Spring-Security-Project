package com.spring.security.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/index")
	public String userIndex() {
		return "index";
	}

	@GetMapping("/admin")
	public String admin() {
		return "/admin";
	}
	
	@RequestMapping("/accessdenied")
	public ModelAndView accessdenied() {
		return new ModelAndView("accessdenied");
	}
	
}
