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

	public enum SALESTATE {
		AVAILABLE, SOLD, OUTSTANDING
	}

	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String description;
	private LocalDate date;
	private double cost;
	private SALESTATE state;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Sale() {

	}

	public Sale(String title, String description, double cost, User user) {
		super();
		this.title = title;
		this.description = description;
		this.date = LocalDate.now();
		this.cost = cost;
		this.user = user;
		this.state = SALESTATE.AVAILABLE;
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

	public SALESTATE getState() {
		return state;
	}


	public void setState(SALESTATE state) {
		this.state = state;
	}
	
	


	public void bought() {
		this.state=SALESTATE.SOLD;
	}
	public boolean wasBought() {
		return state == SALESTATE.SOLD;
	}

}
