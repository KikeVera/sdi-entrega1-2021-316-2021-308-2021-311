package com.uniovi.repositories;




import org.springframework.data.repository.CrudRepository;


import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {
	
	//Busqueda de usuario por su email
	User findByEmail(String email);
	

	

}
