package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.spring.security.entity.User;
import com.spring.security.service.UserService;

import org.apache.ibatis.annotations.Mapper;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spring.security"})
@EntityScan("com.spring.security.entity")
public class Application {
	
	public static void main(String[] args) throws Exception{
		ApplicationContext context=SpringApplication.run(Application.class, args);
		
//		UserService userservice=context.getBean(UserService.class);
//		User user=new User();
//		user.setUsername("user");
//		user.setPassword("pass");
//		user.setRole("user");
//		userservice.saveUser(user);
		
	}
}
