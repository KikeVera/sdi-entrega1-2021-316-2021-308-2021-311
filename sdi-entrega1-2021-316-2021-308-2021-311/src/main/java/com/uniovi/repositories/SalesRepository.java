package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;

public interface SalesRepository extends CrudRepository<Sale, Long>{

	List<Sale> findAllByUser(User user);
	
	

}
