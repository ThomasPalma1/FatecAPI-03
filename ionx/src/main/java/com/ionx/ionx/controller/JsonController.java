package com.ionx.ionx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	

	@PutMapping("edit/{id}")
    Prospect editOne(@RequestBody Prospect prospect, @PathVariable("id") int id) {
        //We will change this to handle error
        Prospect p = prospectService.findById(id).get();
        p.setStatus(prospect.getStatus());
        return prospectService.saveProspect(p);
    }
	
}
