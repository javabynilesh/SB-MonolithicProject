package com.mono.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mono.util.JwtUtil;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

 		String token = request.getHeader("Authorization");
 		
 		if(token != null) {
 			
 			String username = jwtUtil.getUsername(token);
 			
 			if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
 				UserDetails user = userDetailsService.loadUserByUsername(username);
 				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
 						username, 
 						user.getPassword(), 
 						user.getAuthorities());
 				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
 				SecurityContextHolder.getContext().setAuthentication(authentication);
 				
 			}
 		}
 		filterChain.doFilter(request, response);
	}

}
