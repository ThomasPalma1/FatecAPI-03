package com.ionx.ionx.service;

import org.springframework.stereotype.Service;

import com.ionx.ionx.domain.User;
import com.ionx.ionx.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;
	
	public UserService (UserRepository repository) {
		this.repository = repository;
	}
	
	public User save (User user){
		repository.save(user);
		
		return user;
		
	}
		
	
	
}
