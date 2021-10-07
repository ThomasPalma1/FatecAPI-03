package com.ionx.ionx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ionx.ionx.domain.User;
import com.ionx.ionx.repositories.UserRepository;
import com.ionx.ionx.service.UserService;

@RestController
@RequestMapping("/users")

public class UserController {
	@Autowired	
	UserRepository userRepository;
	
	private final UserService userService;
	public UserController(@Lazy UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/create")
	User newUser(@RequestBody User newUser) {
		return userService.save(newUser);
	}
	
	
	
}
