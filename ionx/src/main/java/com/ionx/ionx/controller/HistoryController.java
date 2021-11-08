package com.ionx.ionx.controller;

import com.ionx.ionx.domain.History;
import com.ionx.ionx.service.HistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
@Controller
@RequestMapping("prospects/{prospectId}/historys")
public class HistoryController {

	
	  @Autowired
	    private HistoryService historyService;

	    @GetMapping("/listar")
	    public ModelAndView listar(@PathVariable("prospectId") long prospectId, ModelMap model) {
	        model.addAttribute("historys", historyService.recuperarPorProspect(prospectId));
	        model.addAttribute("prospectId", prospectId);
	        
	        return new ModelAndView("/history/list", model);
	    }

	    @GetMapping("/cadastro")
	    public String preSalvar(ServletRequest request, @ModelAttribute("history") History history, @PathVariable("prospectId") long prospectId) {
	    	HttpSession session = ((HttpServletRequest) request).getSession(true);
			
			String permissao = session.getAttribute("tipo").toString();
			if(permissao.equals("1")) {
		    	return "/history/add";
			}
			else {
				return "home";
			}
	    	
	    }

	    @PostMapping("/salvar")
	    public String salvar(@PathVariable("prospectId") long prospectId, @Valid @ModelAttribute("history")
	          History history, BindingResult result, RedirectAttributes attr) {
	        if (result.hasErrors()) {
	            return "/history/add";
	        }

	        historyService.salvar(history, prospectId);
	        attr.addFlashAttribute("mensagem", "Histórico salvo com sucesso.");
	        return "redirect:/prospects/" + prospectId + "/historys/listar";
	    }

	    @GetMapping("/atualizar")
	    public ModelAndView preAtualizar(@PathVariable("prospectId") long prospectId, @PathVariable("historyId")
	          long historyId, ModelMap model) {
	        History history = historyService.recuperarPorProspectIdEHistoryId(prospectId, historyId);
	        model.addAttribute("history", history);
	        model.addAttribute("prospectId", prospectId);
	        return new ModelAndView("/history/add", model);
	    }

	    @PutMapping("/salvar")
	    public ModelAndView atualizar(@PathVariable("prospectId") long prospectId, @Valid @ModelAttribute("history")
	          History history, BindingResult result, RedirectAttributes attr) {
	        if (result.hasErrors()) {
	            return new ModelAndView("/history/add");
	        }

	        historyService.atualizar(history, prospectId);
	        attr.addFlashAttribute("mensagem", "Histórico atualizada com sucesso.");
	        return new ModelAndView("redirect:/prospects/" + prospectId + "/historys/listar");
	    }

	    @GetMapping("/remover")
	    public String remover(@PathVariable("prospectId") long prospectId, @PathVariable("historyId")
	          long historyId, RedirectAttributes attr) {
	        historyService.excluir(prospectId, historyId);
	        attr.addFlashAttribute("mensagem", "Histórico excluída com sucesso.");
	        return "redirect:/prospects/" + prospectId + "/historys/listar";
	    }
}
