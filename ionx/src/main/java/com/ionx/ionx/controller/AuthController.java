package com.ionx.ionx.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ionx.ionx.domain.User;
import com.ionx.ionx.repositories.UserRepository;

@RestController
@RequestMapping("/auth")

public class AuthController {
	@Autowired
	UserRepository userRepository;

	@PostMapping("/verificarLogin")
	public ResponseEntity<?> auth(@RequestParam(name = "email") String email,
			@RequestParam(name = "senha") String senha, ServletRequest request) {

		User user = userRepository.findByEmail(email);

		if (user != null && senha.equals(user.getSenha())) {

			HttpSession session = ((HttpServletRequest) request).getSession(true);

			session.setAttribute("email", user.getEmail());
			session.setAttribute("tipo", user.getIdPosition());

			return new ResponseEntity<Long>(user.getIdPosition().getId(), HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/logout")
	public ResponseEntity<Boolean> logout(ServletRequest request) {
		HttpSession session = ((HttpServletRequest) request).getSession(false);

		session.invalidate();

		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

}