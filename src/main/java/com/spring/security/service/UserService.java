package com.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.domain.UserDetail;
import com.spring.security.entity.User;
import com.spring.security.mapper.UserMapper;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserMapper usermapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usermapper.saveUser(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usermapper.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + " is not found");
		}
		return new UserDetail(user);
	}

}
