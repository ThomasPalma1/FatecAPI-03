package com.ionx.ionx.controller;

import java.util.List;

import javax.persistence.OrderBy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ionx.ionx.domain.Positions;
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
		User user = new User();

		user.setNome(newUser.getNome());
		user.setSobrenome(newUser.getSobrenome());
		user.setContato(newUser.getContato());
		user.setEmail(newUser.getEmail());
		user.setSenha(newUser.getSenha());
		user.setPosition(new Positions(4));

		return userRepository.save(user);
	}

	@GetMapping("/all")
	List<User> getAllUser() {
		return userRepository.findAll();
	}

	@PostMapping("/updatePosition")
	public ResponseEntity<?> updatePosition(@RequestParam("userID") String id,
			@RequestParam("selectedPosition") String position) {
		User user = userRepository.findById(Long.parseLong(id)).get();
		user.setPosition(new Positions(Integer.parseInt(position)));
		userRepository.save(user);

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
