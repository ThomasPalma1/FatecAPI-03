package com.ionx.ionx.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ionx.ionx.domain.Produto;
import com.ionx.ionx.service.ProdutoService;

@Controller
@RequestMapping("produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	// Associar com list.html
	@GetMapping("/listar")
	public ModelAndView listar(ModelMap model) {
		model.addAttribute("produtos", produtoService.recuperar());
		return new ModelAndView("produto/list_produto", model);
	}

	// Associar com add.html
	@GetMapping("/cadastro")
	public String preSalvar(@ModelAttribute("produto") Produto produto) {
		return "/produto/add_produto";
	}

	// Metódo para salvar --> Tipo POST
	@PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("produto") Produto produto, @RequestParam("file") MultipartFile photo, ModelMap model, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
            return "/produto/add_produto";
        }
		
        if (photo.isEmpty()) {
        	return "produto";
        }
        
        Path path = Paths.get("uploads/");
        try {
        	InputStream inputStream = photo.getInputStream();
        	Files.copy(inputStream, path.resolve(photo.getOriginalFilename()),
        			StandardCopyOption.REPLACE_EXISTING);
        	
        	produto.setPhoto(photo.getOriginalFilename().toLowerCase());
        } catch(Exception e) {
        	e.printStackTrace();
        }

        produtoService.salvar(produto);
        attr.addFlashAttribute("mensagem", "Produto salvo com sucesso.");
        return "redirect:/produtos/listar";
    }
	
	
	// Atualização de produtos
	@GetMapping("/atualizar")
	public ModelAndView preAtualizar(@RequestParam("id") long id, ModelMap model) {
		Produto produto = produtoService.recuperarPorId(id);
		model.addAttribute("produto", produto);
		return new ModelAndView("/produto/add_produto", model);
	}

	// Remover produto

	@GetMapping("/remover")
	public String remover(@RequestParam("id") long id, RedirectAttributes attr) {
		produtoService.excluir(id);
		attr.addFlashAttribute("mensagem", "Produto excluído com sucesso.");
		return "redirect:/produtos/listar";
	}

}
