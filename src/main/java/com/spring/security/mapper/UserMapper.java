package com.spring.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.spring.security.entity.User;

@Mapper
@Component
public interface UserMapper {
	User findByUsername(@Param("username") String username);
	int saveUser(@Param("param") User user);

}
