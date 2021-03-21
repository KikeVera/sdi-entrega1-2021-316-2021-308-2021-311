package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.repositories.ConversationRepository;

@Service
public class ConversationsService {

	@Autowired
	private ConversationRepository conversationRepository;

	
	public List<Conversation> getConversations() {
		List<Conversation> conversations = new ArrayList<Conversation>();
		conversationRepository.findAll().forEach(conversations::add);
		return conversations;
	}

	public Conversation getConversation(Long id) {
		return conversationRepository.findById(id).get();
	}
	
	public List<Conversation> getConversationsBySale(Sale sale) {
		List<Conversation> conversations = new ArrayList<Conversation>();
		conversationRepository.findBySale(sale).forEach(conversations::add);
		return conversations;
	}
	public List<Conversation> getConversationsByUser(User user) {
		List<Conversation> conversations = new ArrayList<Conversation>();
		conversationRepository.findByUser(user).forEach(conversations::add);
		return conversations;
	}
	
	public Conversation getConversationsByUserAndSale(User user,Sale sale) {
		Conversation conversation;
		conversation = conversationRepository.findByUserAndSale(user,sale);
		if(conversation == null) {
			conversation = new Conversation(user,sale);
			conversationRepository.save(conversation);
		}
		return conversation;
	}

	public void addConversation(Conversation sale) {
		conversationRepository.save(sale);
	}

	public void deleteConversation(Long id) {
		conversationRepository.deleteById(id);
	}
	
	public List<Message> getMessageOfAConversation(Long id){
		List<Message> messages = new ArrayList<Message>();
		conversationRepository.findMessageById(id).forEach(messages::add);
		return messages;
	}
}
