package com.spring.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyErrorController implements ErrorController{
 
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String renderErrorPage(HttpServletRequest httpRequest,Model model) {
       
        int httpErrorCode = getErrorCode(httpRequest);
        System.out.print(httpErrorCode);
        if (httpErrorCode==404) {
            return "notfound";
            }
       return "getErrorPath";
    }
     
    
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
