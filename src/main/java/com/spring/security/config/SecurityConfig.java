package com.spring.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.spring.security.controller.CustomAccessDeniedHandler;
import com.spring.security.controller.CustomAuthenticationSuccessHandler;


@Configuration
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
         .antMatchers("/expired").permitAll()
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
         .and().csrf().disable();
    	 http.formLogin()
 		.loginPage("/login")
 		.defaultSuccessUrl("/index", true)
 		.failureHandler(customAuthenticationFailureHandler());
         http.sessionManagement()
     	.maximumSessions(1)               
     	.maxSessionsPreventsLogin(true)    
     	.expiredUrl("/login") 
     	.sessionRegistry(sessionRegistry()) ;
         http.sessionManagement().sessionFixation().migrateSession();
         http.logout()
 	    .invalidateHttpSession(true)
 	    .deleteCookies("JSESSIONID")
 		.permitAll();
    }
    
    @Bean
    SessionRegistry sessionRegistry() {			
        return new SessionRegistryImpl();
    }
    
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {	
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
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
    
    @Bean
	public AuthenticationFailureHandler customAuthenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}
	
}
