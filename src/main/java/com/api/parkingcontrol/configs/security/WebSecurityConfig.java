package com.api.parkingcontrol.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
	
	@Bean
	// @Authentication
	public UserDetailsService userDetailsService(){
		/*
		UserDetails admin = User.withUsername("igor")
				.password(encoder.encode("senha123"))
				.roles("ADMIN")
				.build();
		UserDetails user = User.withUsername("ana")
				.password(encoder.encode("senha123"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(admin, user);
		*/
		return new UserModelUserDatailsService();
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {	
		http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers(HttpMethod.GET, "/parking-spot").permitAll()
        .and()
        .authorizeHttpRequests()
        .requestMatchers(HttpMethod.POST, "/parking-spot/new").permitAll()
        .and()
		.httpBasic();
		return http.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
}
