package com.uniovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	//Petición para ir al índice de la aplicación
	@RequestMapping("/")
	public String index() {
		return"index";
	}

}
