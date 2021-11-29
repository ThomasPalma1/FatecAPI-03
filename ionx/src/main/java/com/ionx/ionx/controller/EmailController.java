package com.ionx.ionx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ionx.ionx.domain.Prospect;
import com.ionx.ionx.repositories.ProspectRepository;
import com.ionx.ionx.sendEmail.EmailService;

@RestController
@RequestMapping("/email")

public class EmailController {
	@Autowired
	ProspectRepository prospectRepository;

	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@RequestParam(name = "prospectId") long id) {
		Prospect p = prospectRepository.findById(id).get();
		String email = p.getEmail();
		EmailService es = new EmailService();
		es.sendSimpleEmail("Atualização na Prospect " + p.getNome(),
				"Acabou de acontecer uma atualização na Prospect " + p.getNome() + "!", email);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
