package com.uniovi.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sale")
public class Sale {

	

	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String description;
	private LocalDate date;
	private double cost;
	private boolean available=true;
	private boolean outstanding=false;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "buyer_id")
	private User buyer;

	public Sale() {

	}

	public Sale(String title, String description, double cost, User user) {
		super();
		this.title = title;
		this.description = description;
		this.date = LocalDate.now();
		this.cost = cost;
		this.user = user;
		
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getDate() {
		return date;
	}

	public double getCost() {
		return cost;
	}

	public User getUser() {
		return user;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isOutstanding() {
		return outstanding;
	}

	public void setOutstanding(boolean outstanding) {
		this.outstanding = outstanding;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	
	

}
