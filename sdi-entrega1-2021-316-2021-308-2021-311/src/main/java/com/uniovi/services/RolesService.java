package com.uniovi.services;

import org.springframework.stereotype.Service;

@Service
public class RolesService {
	//Hay dos roles posibles, de usuario y de administrador
	String[] roles= {"ROLE_USER","ROLE_ADMIN"};
	
	public String[] getRoles() {
		return roles;
	}

}
