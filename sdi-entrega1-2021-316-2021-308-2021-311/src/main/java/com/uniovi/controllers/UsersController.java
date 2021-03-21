package com.uniovi.controllers;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;


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
import com.uniovi.validators.LoginFormValidator;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	
	//Saldo por defecto de un usuario
	public static final double INITMONEY=100;
	
	//Inyección de servicios necesarios en este controlador
	@Autowired 
	private UsersService usersService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	@Autowired
	private LoginFormValidator loginFormValidator;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private HttpSession httpSession;
	
	//Petición  que devuelve la lista de usuarios
	@RequestMapping("/user/list")
	public String getList(Model model){
		
		//Obtenemos la lista de usuarios y la añadimos al modelo
		List<User> listaUsuarios;
	
		listaUsuarios=usersService.getUsers();
		
		model.addAttribute("usersList", listaUsuarios );
		return "user/list";
	}
	
	//Petición  que devuelve la lista de usuarios
		@RequestMapping("/user/list/update")
		public String updateList(Model model){
			
			//Obtenemos la lista de usuarios y la añadimos al modelo
			List<User> listaUsuarios;
		
			listaUsuarios=usersService.getUsers();
			
			model.addAttribute("usersList", listaUsuarios );
			return"user/list :: tableUsers";
		}
	
	//Petición  que borra los usuarios marcados con la checkbox
	@RequestMapping(value="/user/delete", method = RequestMethod.POST)
	public String deleteUser(@RequestParam(value = "checkboxUser", required = false) String checkboxValue[]){
		
		//Para todas las checkbox marcadas (las que nos llegan debido a el required=false) borramos el usuario a partor del email obtenido del valor de las checkbox
		for(String email:checkboxValue) {
			usersService.deleteUser(usersService.getUserByEmail(email).getId());
		}

		
		return"redirect:/user/list";
	}
	
	//Petición que devuelve la vista de login 
	@RequestMapping(value ="/login",method =RequestMethod.GET)
	public String login(Model model){
		model.addAttribute("user", new User());
		return"login";
	}
	
	//Petición GET de custom login por si se intenta acceder escribiendo la URL, redirige a login
	@RequestMapping(value ="/customLogin",method =RequestMethod.GET)
	public String customLogin(){
		return"redirect:/login";
	}
	
	//Petición que obtiene el intento de login
	@RequestMapping(value ="/customLogin",method =RequestMethod.POST)
	public String customLogin(@Validated User user, BindingResult result){
		
		//Validamos los campos del login
		loginFormValidator.validate(user,result);
		
		//Si tiene errores los muestra
		if(result.hasErrors()) {
			return"login";
		}
		
		//Si está todo bien añade el atributo se sesión para mostrar el saldo y logea el usuario
		httpSession.setAttribute("user",usersService.getUserByEmail(user.getEmail()));
		securityService.autoLogin(user.getEmail(),user.getPassword());
		return"redirect:/home";
		
	}
	
	
	
	//Petición que lleva al formulario de registro
	@RequestMapping(value ="/signup",method =RequestMethod.GET)
	public String signup(Model model){
		model.addAttribute("user", new User());
		return"signup";
		
	}
	
	
	//Petición que obtiene el intento de registro
	@RequestMapping(value ="/signup",method=RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result){
		
		//Validación de los campos del resgistro
		signUpFormValidator.validate(user,result);
		
		//Si tiene errores te los muestra
		if(result.hasErrors()) {
			return "signup";
		}
		
		//Si está todo bien se le asigna el rol de usuario y el saldo inical
		user.setRole(rolesService.getRoles()[0]);
		user.setMoney(INITMONEY);
		//Se añade el usuario a la base de datos y se logea
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(),user.getPasswordConfirm());
		httpSession.setAttribute("user",user);
		return"redirect:/home";
	}
	
	
	
	@RequestMapping(value ={"/home"},method =RequestMethod.GET)
	public String home(Model model){
	
		return "home";
	}
	
	
	

}
