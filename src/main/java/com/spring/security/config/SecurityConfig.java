package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import com.spring.security.controller.CustomAccessDeniedHandler;
import com.spring.security.controller.CustomAuthenticationSuccessHandler;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
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
         .and()
         .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
         .and().csrf().disable(); // we'll enable this in a later blog post
    }
    
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
      UserDetails user = User.withDefaultPasswordEncoder().username("user").password("pass").roles("USER").build();
      UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("adpass").roles("ADMIN").build();

      return new InMemoryUserDetailsManager(user , admin);
    }
    
    
    @Bean
    public CustomAccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
    
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}