package com.ionx.ionx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ionx.ionx.domain.Produto;
import com.ionx.ionx.domain.Prospect;
import com.ionx.ionx.service.ProdutoService;
import com.ionx.ionx.service.ProspectService;

@RestController
public class JsonController {
	
	@Autowired
	private ProspectService prospectService;
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping("/prospectcharts")
	public List<Prospect> prospect() {
		List<Prospect> prospect = prospectService.recuperar();
				
	    return prospect;
	}
	
	@RequestMapping("/produtocharts")
	public List<Produto> produto() {
		List<Produto> produto = produtoService.recuperar();
				
	    return produto;
	}
	
	
}
