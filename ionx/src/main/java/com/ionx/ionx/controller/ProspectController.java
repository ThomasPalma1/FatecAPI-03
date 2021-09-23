package com.ionx.ionx.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ionx.ionx.domain.Prospect;
import com.ionx.ionx.service.ProdutoService;
import com.ionx.ionx.service.ProspectService;

@Controller
@RequestMapping("prospects")
public class ProspectController {
	
	@Autowired
	private ProspectService prospectService;
	@Autowired
	private ProdutoService produtoService;
	
	//Associar com list.html
	@GetMapping("/listar")
	public ModelAndView listar(ModelMap model) {
		model.addAttribute("prospects", prospectService.recuperar());
		return new ModelAndView("prospect/list", model);
	}
	
	@GetMapping("/prospect")
	public ModelAndView negocios(ModelMap model) {
		model.addAttribute("prospects", prospectService.recuperar());
		return new ModelAndView("prospect/negocios", model);
	}
	
	//Associar com add.html
    @GetMapping("/cadastro")
    public ModelAndView preSalvar(@ModelAttribute("prospect") Prospect prospect, ModelMap model) {
    	model.addAttribute("produtos", produtoService.recuperar());
    	return new ModelAndView("prospect/add", model);
    }
    
    //Metódo para salvar --> Tipo POST
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("prospect") Prospect prospect, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/prospect/add";
        }

        prospectService.salvar(prospect);
        attr.addFlashAttribute("mensagem", "Prospect salvo com sucesso.");
        return "redirect:/prospects/listar";
    }
    
  //Atualização de prospects
    @GetMapping("/atualizar")
    public ModelAndView preAtualizar(@RequestParam("id") long id, ModelMap model) {
    	Prospect prospect = prospectService.recuperarPorId(id);
        model.addAttribute("prospect", prospect);
        return new ModelAndView("/prospect/add", model);
    }

    //Remover prospect

    @GetMapping("/remover")
    public String remover(@RequestParam("id") long id, RedirectAttributes attr) {
    	prospectService.excluir(id);
        attr.addFlashAttribute("mensagem", "prospect excluído com sucesso.");
        return "redirect:/prospects/listar";
    }

    
}
