package com.ionx.ionx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ionx.ionx.domain.Prospect;
import com.ionx.ionx.service.ReadFileService;

@Controller
public class ReadFileController {
	@Autowired
	private ReadFileService readFileService;
	
	@GetMapping("/includeFile")
	public String home(Model model) {
		model.addAttribute("prospect", new Prospect());
		List<Prospect> prospects= readFileService.findAll();
		model.addAttribute("prospects", prospects);
		return "prospect/includefile";
	}
	
@PostMapping("/fileupload")
public String uploadFile(@ModelAttribute Prospect prospect, RedirectAttributes redirectAttributes) {
	boolean isFlag = readFileService.saveDataFromUploadfile(prospect.getFile());
	if(isFlag) {
		redirectAttributes.addFlashAttribute("successmessage", "Arquivo importado com sucesso!");
	}else {
		redirectAttributes.addFlashAttribute("errormessage", "Não foi possível realizar a importação, por favor, tente novamente.");
	}
	
	return "redirect:/home";
}

}
