package com.uniovi.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Message {

	@ManyToOne
	@JoinColumn(name = "usermessage_id") 
	private User user;
	
	private String message;
	
	private LocalDate date;
	
	private LocalTime time;
	
	public Message() {
		
	}

	public Message(User user2, String message2) {
		this.user = user2;
		this.message = message2;
		this.date = LocalDate.now();
		this.setTime(LocalTime.now());
	}

	public User getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
	
}
