package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.spring.security.controller.CustomAuthenticationSuccessHandler;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http
         .authorizeRequests()
         .antMatchers( "/css/**").permitAll()
         .antMatchers("/admin").hasRole("ADMIN")
         .anyRequest().authenticated()
         .and()
         .formLogin()
         .loginPage("/login")
         .successHandler(authenticationSuccessHandler)
         .permitAll()
         .and()
         .logout()
         .permitAll()
         .and().csrf().disable(); // we'll enable this in a later blog post
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//            .and()
//                .withUser("manager").password("password").roles("MANAGER");
//    }
    
    
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
      UserDetails user = User.withDefaultPasswordEncoder().username("user").password("pass").roles("USER").build();
      UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("adpass").roles("ADMIN").build();

      return new InMemoryUserDetailsManager(user , admin);
    }

}