package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.services.ConversationsService;
import com.uniovi.services.SalesService;
import com.uniovi.services.UsersService;

@Controller
public class ConversationsController {

	@Autowired
	private SalesService salesService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ConversationsService conversationsService;
	
	@RequestMapping("/sale/conversation/{id}")
	public String getConversation(Model model,@PathVariable Long id,@RequestParam(value = "",required=false) String message) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		Sale sale = salesService.getSale(id);
		Conversation conversation = conversationsService.getConversationsByUserAndSale(user,sale);
		if(message != null && !message.isEmpty()) {
			Message m = new Message(user,message);
			conversation.AddMessage(m);
			conversationsService.addConversation(conversation);
		}
		message = null;
		model.addAttribute("messages", conversationsService.getMessageOfAConversation(conversation.getId()));	
		model.addAttribute("sale", "/sale/conversation/" + sale.getId());
		
		return "sale/conversation";
	}
	
	@RequestMapping("/conversation/list")
	public String getList(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		
		model.addAttribute("userConversation", conversationsService.getConversationsByUser(user));
		
		
		return "conversation/list";
	}
}
