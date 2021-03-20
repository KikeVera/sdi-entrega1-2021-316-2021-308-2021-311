package com.uniovi.entities;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Message {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "conversation")
	private Conversation conversation;
	
	private LocalDate date;
	
	public Message() {
		
	}
	
	public User getUser() {
		return user;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
