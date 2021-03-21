package com.uniovi.services;

import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {
	
	@Autowired 
	private UsersRepository usersRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	//Obtención de todos los usarios del sistema
	public List<User> getUsers(){
		List<User> users= new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}
	
	//Obtención de un usuario por su id
	public User getUser(Long id){
		return usersRepository.findById(id).get();
	 }
	
	//Sñadimos un usuario a la base de datos
	public void addUser(User user){
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}
	
	////Guardamos un usario editado sin encriptar d enuevo el password
	public void editUser(User user){
		
		usersRepository.save(user);
	}
	
	//Borramos un usario por su id
	public void deleteUser(Long id){
		usersRepository.deleteById(id);
	}
	
	//Obtención de un usuario por su email
	
	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
	
	//Actualizamos el saldo de un usuario
	public void updateMoney(User user,double money) {
		user.setMoney(user.getMoney()+money);
		usersRepository.save(user);
	}
	
	
}


