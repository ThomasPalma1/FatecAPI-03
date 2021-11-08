package com.ionx.ionx.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "cadastro/login";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "cadastro/admin";
	}

	@RequestMapping("/home")
	public String home(ServletRequest request) {
		

		HttpSession session = ((HttpServletRequest) request).getSession(true);
		
		String permissao = session.getAttribute("tipo").toString();
		if(permissao.equals("1")) {
			
			return "home";
		}
		
		else {
			return "homeIndefinido";
		}
	}

	@RequestMapping("/cadastro")
	public String cadastro() {
		return "cadastro/cadastro";
	}

	@RequestMapping("/realizarLogin")
	public String realizarLogin() {
		return "cadastro/cadastro";
	}
	@RequestMapping("/adminconfig")
		public String adminconfig(ServletRequest request) {
		
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		
		String permissao = session.getAttribute("tipo").toString();
		if(permissao.equals("1")) {
			
			return "admin";
		}
		
		else {
			return "homeIndefinido";
		}
	}



}
