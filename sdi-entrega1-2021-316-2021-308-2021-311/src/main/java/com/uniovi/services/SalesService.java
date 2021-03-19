package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.repositories.SalesRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class SalesService {

	@Autowired
	private SalesRepository salesRepository;
	@Autowired
	private UsersRepository userRepository;

	public List<Sale> getSales() {
		List<Sale> sales = new ArrayList<Sale>();
		salesRepository.findAll().forEach(sales::add);
		return sales;
	}

	public Sale getSale(Long id) {
		return salesRepository.findById(id).get();
	}

	public void addSale(Sale sale) {
		salesRepository.save(sale);
	}

	public void deleteSale(Long id) {
		salesRepository.deleteById(id);
	}

	public List<Sale> getSaleByUser(User user) {
		List<Sale> sales = new ArrayList<Sale>();
		salesRepository.findAllByUser(user).forEach(sales::add);
		return sales;
	}
	
	public List<Sale> searchSalesByTitleAndUser (String searchText,User user){
		searchText = "%"+searchText+"%";
		return salesRepository.searchSalesByTitleAndUser(searchText,user,"OUTSTANDING");
	}
	
	
	public Page<Sale> getShoppingForUser(Pageable pageable,User user){
		Page<Sale> sales= new PageImpl<Sale>(new LinkedList<Sale>());
		
		sales= salesRepository.searchByShoppingToUser(pageable, user);
		
		return sales;
	}
	
	public Page<Sale> getShoppingForUser(Pageable pageable,String searchtext, User user){
		Page<Sale> sales= new PageImpl<Sale>(new LinkedList<Sale>());
		searchtext = "%"+searchtext+"%";
		sales= salesRepository.searchByTitleAndShoppingToUser(pageable, searchtext, user);
		
		return sales;
	}

	public boolean buy(User user, Sale sale) {
		if(user.getMoney() - sale.getCost()>=0 && !sale.wasBought()) {
			user.setMoney(user.getMoney() - sale.getCost());
			sale.bought();
			salesRepository.save(sale);
			userRepository.save(user);
			return true;
		}
		return false;
	}
}