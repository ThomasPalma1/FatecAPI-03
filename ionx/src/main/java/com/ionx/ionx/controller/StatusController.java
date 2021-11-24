package com.ionx.ionx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ionx.ionx.domain.Status;
import com.ionx.ionx.repositories.StatusRepository;

@RestController
@RequestMapping("/status")
public class StatusController {

	@Autowired
	StatusRepository statusRepository;
	
	@GetMapping("/allStatus")
	public List<Status> getAllPositions(){
		return statusRepository.findAll();
	}
}
