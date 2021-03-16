package com.uniovi.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	
	public static final double INITMONEY=100;
	
	@Autowired //Inyectarel servicio
	private UsersService usersService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	@Autowired
	private RolesService rolesService;
	
	@RequestMapping("/user/list")
	public String getList(Model model){
		
		List<User> listaUsuarios;

	
		listaUsuarios=usersService.getUsers();
		
		
		model.addAttribute("usersList", listaUsuarios );
		return "user/list";
	}
	
	@RequestMapping(value ="/login",method =RequestMethod.GET)
	public String login(){
		
		return"login";
	}
	
	@RequestMapping(value ="/signup",method =RequestMethod.GET)
	public String signup(Model model){
		model.addAttribute("user", new User());
		return"signup";
		
	}
	
	@RequestMapping(value ="/signup",method=RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result){
		signUpFormValidator.validate(user,result);
		
		if(result.hasErrors()) {
			return "signup";
		}
		user.setRole(rolesService.getRoles()[0]);
		user.setMoney(INITMONEY);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(),user.getPasswordConfirm());
		return"redirect:/home";
	}
	
	
	
	@RequestMapping(value ={"/home"},method =RequestMethod.GET)
	public String home(Model model){
		Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		String email =auth.getName();
		User activeUser =usersService.getUserByEmail(email);
		model.addAttribute("user",activeUser);
		return "home";
	}
	
	
	

}
