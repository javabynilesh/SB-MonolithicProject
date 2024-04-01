package com.mono.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mono.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
}
