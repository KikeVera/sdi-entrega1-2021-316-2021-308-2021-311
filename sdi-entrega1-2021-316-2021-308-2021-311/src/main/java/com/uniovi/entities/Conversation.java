package com.uniovi.entities;


import java.util.HashSet;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "conversation")
public class Conversation {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;
	
	@ManyToOne
	@JoinColumn(name = "customer_id") 
	private User customer;
	
	@ElementCollection
	@CollectionTable(name="TMessages")
	private Set<Message> messages = new HashSet<>();
	
	public Conversation() {
		
	}

	public Conversation(User user, Sale sale2) {
		this.customer = user;
		this.sale = sale2;
		this.messages= new HashSet<Message>();
	}

	public long getId() {
		return id;
	}

	public Sale getSale() {
		return sale;
	}

	public User getCustomer() {
		return customer;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	public void AddMessage(Message message) {
		messages.add(message);
	}
}
