package com.mono.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mono.entity.User;
import com.mono.repo.UserRepository;
import com.mono.service.IUserService;
import com.mono.util.JwtUtil;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired //kn
	private JwtUtil jwtUtil;
	
	public Integer saveUser(User user) {
		String encPwd = encoder.encode(user.getPassword());
		user.setPassword(encPwd);
		return userRepository.save(user).getId();
	}

	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if(user != null)
			return user;
		else
			return null;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException 
	
	{
		User user = findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException(username+", NOT EXIST");
		
		List<GrantedAuthority> list =
		user.getRoles()
			.stream()
			.map(role -> new SimpleGrantedAuthority(role))
			.collect(Collectors.toList());
		
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), 
				user.getPassword(), 
				list);
	}

	@Override
	public User findUserByJwtToken(String jwt) throws Exception {
		// TODO Auto-generated method stub
		String username = jwtUtil.getUsername(jwt);
		User user = findUserByEmail(username);
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		User user = userRepository.findByUsername(email);
		if(user==null) {
			throw new Exception("User not Found By Email");
		}
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePassword(User user, String newPassword) {
		// TODO Auto-generated method stub
		
	}

}
