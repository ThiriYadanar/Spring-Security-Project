package com.spring.security.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class User {
	private Integer id;
	private String user_name;
	private String password;
	private String role;
	public User(String user_name, String password,String role) {
        this.user_name = user_name;
        this.password = password;
        this.role = role;
    }
	
}
