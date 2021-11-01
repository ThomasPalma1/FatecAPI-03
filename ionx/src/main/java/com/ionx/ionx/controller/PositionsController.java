package com.ionx.ionx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ionx.ionx.domain.Positions;
import com.ionx.ionx.repositories.PositionsRepository;

@RestController
@RequestMapping("/positions")
public class PositionsController {

	@Autowired
	PositionsRepository positionsRepository;
	
	@GetMapping("/all")
	public List<Positions> getAllPositions(){
		return positionsRepository.findAll();
	}
}
