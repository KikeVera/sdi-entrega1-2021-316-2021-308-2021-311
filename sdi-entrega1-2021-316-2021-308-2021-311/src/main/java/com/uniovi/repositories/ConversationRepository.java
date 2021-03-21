package com.uniovi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;

public interface ConversationRepository extends CrudRepository<Conversation, Long>{

	Iterable<Conversation> findBySaleId(Sale sale);

	Iterable<Conversation> findBySale(Sale sale);

	@Query("SELECT c FROM Conversation c WHERE c.customer = ?1")
	Iterable<Conversation> findByUser(User user);
	
	@Query("SELECT c FROM Conversation c WHERE c.customer = ?1 and sale = ?2")
	Conversation findByUserAndSale(User user, Sale sale);

	@Query("SELECT m FROM Conversation c inner join c.messages m WHERE c.id = ?1 ORDER BY m.time")
	Iterable<Message> findMessageById(Long id);

}
