package com.loginRegister.LoginRegister.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.AbstractBeansOfTypeDatabaseInitializerDetector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebConfig {

	
	@Bean
	public UserDetailsService userDetailsService() {
			return new CustomUserDetailsService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeHttpRequests().requestMatchers("/admin").hasAuthority("ADMIN").requestMatchers("/index").hasAuthority("USERS")
		.requestMatchers("/register", "/add","/register/admin","/add/admin").permitAll()
		.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll();
		return httpSecurity.build();
	}
	
}
