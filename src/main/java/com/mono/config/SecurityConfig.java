package com.mono.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mono.filter.SecurityFilter;

@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private SecurityFilter securityFilter;

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration configuration)
						throws Exception
	{
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
	
	@Bean
	public SecurityFilterChain configAuthorization(HttpSecurity http) throws Exception {
				http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/api/user/save","/api/user/login",
						"/api/countrylist","/api/country","/api/state/**","/api/city/**").permitAll()
				.antMatchers("/api/user/welcome").hasAnyAuthority("ADMIN","USER")
				.antMatchers("/api/user/profile").hasAnyAuthority("ADMIN","USER")
				.antMatchers("/api/employee/**").hasAuthority("ADMIN")
				.antMatchers("/api/msme/**").hasAuthority("USER")
				.anyRequest().authenticated()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
				
				return http.build();
	}
	
	
}
