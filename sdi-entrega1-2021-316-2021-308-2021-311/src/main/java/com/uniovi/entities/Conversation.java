package com.uniovi.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conversation {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User client;
	
	@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
	private List<Message> messages;
	
	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;
}
