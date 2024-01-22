package com.inn.emp.configuration;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;



@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,jsr250Enabled = true,securedEnabled = true)
@Configuration
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService detailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
			throws Exception {
		
		httpSecurity
			.cors().disable()
			.csrf().disable()
			.authorizeRequests()
            .requestMatchers(new AntPathRequestMatcher("/login/emp/login"),
			        new AntPathRequestMatcher("/emp/signUp/**"))
            .permitAll()
			.requestMatchers(new AntPathRequestMatcher("/emp/**"))//only admin
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
			
			return httpSecurity.build();		
		
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider db = new DaoAuthenticationProvider();
		db.setUserDetailsService(this.detailsService);
		db.setPasswordEncoder(passwordEncoder());
		
		return db;
	}

}
