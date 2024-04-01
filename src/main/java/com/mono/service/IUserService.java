package com.mono.service;

import java.util.List;

import com.mono.entity.User;

public interface IUserService {

	Integer saveUser(User user);
	User findByUsername(String username);
	
	//added by me
	User findUserByJwtToken(String jwt) throws Exception;
	
	User findUserByEmail(String email) throws Exception;
	
	List<User> findAllUsers();
	
	void updatePassword(User user, String newPassword);
	
}
