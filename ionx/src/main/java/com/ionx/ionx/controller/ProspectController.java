package com.ionx.ionx.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public ModelAndView preSalvar(ServletRequest request, ModelMap model, @ModelAttribute("prospect") Prospect prospect){
    	model.addAttribute("produtos", produtoService.recuperar());
    	HttpSession session = ((HttpServletRequest) request).getSession(true);
		
		String permissao = session.getAttribute("tipo").toString();
		if(permissao.equals("1")) {
			
			return new ModelAndView("prospect/add", model);
		}
		else {
			return new ModelAndView("home", model);
		}
    	
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
    public ModelAndView preAtualizar(ServletRequest request, @RequestParam("id") long id, ModelMap model) {
        
    	HttpSession session = ((HttpServletRequest) request).getSession(true);
		
		String permissao = session.getAttribute("tipo").toString();
		if(permissao.equals("1")) {
			Prospect prospect = prospectService.recuperarPorId(id);
	        model.addAttribute("prospect", prospect);
	        model.addAttribute("produtos", produtoService.recuperar());
			 return new ModelAndView("/prospect/add", model);
		}
		
		else {
			return new ModelAndView ("home", model);
		}
    }
    
    //Remover prospect

    @GetMapping("/remover")
    public String remover(ServletRequest request, @RequestParam("id") long id, RedirectAttributes attr) {
        HttpSession session = ((HttpServletRequest) request).getSession(true);
		
		String permissao = session.getAttribute("tipo").toString();
		if(permissao.equals("1")) {
			prospectService.excluir(id);
	        attr.addFlashAttribute("mensagem", "prospect excluído com sucesso.");
			return "/produto/add_produto";
		}
		
		else {
			return "home";
		}
		
    }

	
	@GetMapping("/details")
	public ModelAndView detalhes(ModelMap model, @RequestParam("id") long id, RedirectAttributes attr) {
		Prospect prospect = prospectService.recuperarPorId(id);
        model.addAttribute("prospect", prospect);
		return new ModelAndView("prospect/prospect_detalhes", model);
	}
	
    
    
    
}
