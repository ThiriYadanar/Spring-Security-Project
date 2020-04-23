package com.spring.security.controller;


import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.security.message.Message;
@Controller
public class LoginController {
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping("/login")
	public String login(@AuthenticationPrincipal User user,
			@RequestAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false) AuthenticationException exception,
			Model model) throws Exception {
		if (Objects.nonNull(user)){
			return "index";
		}
		if (Objects.nonNull(exception)) {
			setSessionExceedMessage(exception, model);
		}
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
	
	private void setSessionExceedMessage(AuthenticationException exception, Model model) throws Exception{
		if (exception instanceof SessionAuthenticationException) {
			model.addAttribute("error", messageSource.getMessage(Message.session_msg, null, Locale.JAPAN));
		}
		else if (exception instanceof BadCredentialsException) {
			model.addAttribute("error", messageSource.getMessage(Message.badCredential_msg, null, Locale.JAPAN));
		}
		else {
			throw new Exception("An unexpected error occured");
		}
	}

	
}
