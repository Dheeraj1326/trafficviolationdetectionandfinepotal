package com.tvdfp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/login")
	public String loginPage() {
		return "login"; 
	}

	@GetMapping("/register")
	public String registerPage() {
		return "register"; 
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard"; 
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin"; 
	}
	
	@GetMapping("/receipt")
	public String receipt() {
		return "receipt"; 
	}
	
	@GetMapping("/index")
	public String index() {
		return "index"; 
	}
	
}
