package com.ionx.ionx.controller;

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
	public String home() {
		return "home";
	}

	@RequestMapping("/cadastro")
	public String cadastro() {
		return "cadastro/cadastro";
	}

	@RequestMapping("/realizarLogin")
	public String realizarLogin() {
		return "cadastro/cadastro";
	}

}
