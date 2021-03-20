package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Conversation;

public interface ConversationsRepository extends CrudRepository<Conversation, Long>{

}
