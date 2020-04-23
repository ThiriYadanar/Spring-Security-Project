package com.spring.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {

		httpServletRequest.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, e);
		httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);

	}
}