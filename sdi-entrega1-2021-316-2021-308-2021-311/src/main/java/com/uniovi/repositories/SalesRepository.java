package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;

public interface SalesRepository extends CrudRepository<Sale, Long>{

	List<Sale> findAllByUser(User user);
	
	@Query("SELECT s FROM Sale s WHERE (LOWER(s.title) LIKE LOWER(?1)) AND s.user= ?2 ")
	List<Sale> searchSalesByTitleAndUser(String title,User user);
	
	

}
