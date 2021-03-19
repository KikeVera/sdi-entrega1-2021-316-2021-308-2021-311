package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.repositories.SalesRepository;

@Service
public class SalesService {

	@Autowired
	private SalesRepository salesRepository;

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
		return salesRepository.searchSalesByTitleAndUser(searchText,user);
		}
}
