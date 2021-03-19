package com.uniovi.controllers;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.uniovi.entities.Sale;
import com.uniovi.entities.Sale.SALESTATE;
import com.uniovi.entities.User;
import com.uniovi.services.SalesService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddSaleValidator;

@Controller
public class SalesController {

	@Autowired
	private SalesService salesService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private AddSaleValidator addSaleValidator;

	@RequestMapping("/sale/list")
	public String getList(Model model,@RequestParam(value = "",required=false) String searchText) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		
		List<Sale> userSales = new ArrayList<Sale>();
		if(searchText != null && !searchText.isEmpty()) {
			userSales = salesService.searchSalesByTitleAndUser(searchText,user);
		}else {
			userSales = salesService.getSaleByUser(user);
		}
		model.addAttribute("userSales", userSales);
		return "sale/list";
	}

	@RequestMapping(value = "/sale/add", method = RequestMethod.POST)
	public String setSales(@Validated Sale sale, BindingResult errors,
			@RequestParam(value = "checkbox", required = false) String checkbox) {
		addSaleValidator.validate(sale, errors);
		if (errors.hasErrors()) {
			return "sale/add";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		sale.setUser(user);
		if(checkbox!=null) {
			sale.setState(SALESTATE.OUTSTANDING);
		}
		salesService.addSale(sale);
		return "redirect:/sale/list";
	}

	@RequestMapping(value = "/sale/add", method = RequestMethod.GET)
	public String getSales(Model model) {
		model.addAttribute("sale", new Sale());
		return "sale/add";
	}

	@RequestMapping("/sale/delete/{id}")
	public String deleteSales(@PathVariable Long id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		if(salesService.getSale(id).getUser().getEmail().equals(email)) {
			salesService.deleteSale(id);
		}
		return "redirect:/sale/list";
	}
	
	
	

	@RequestMapping("/sale/shopping")
	public String getList(Model model,Pageable pageable, 
			@RequestParam(value = "", required = false) String searchText) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		Page<Sale> sales= new PageImpl<Sale>(new LinkedList<Sale>());
		
		
		if(searchText==null || searchText.isEmpty()) {
			sales=salesService.getShoppingForUser(pageable, user);
		}
		
		else {
			sales=salesService.getShoppingForUser(pageable, searchText, user);
		}
		model.addAttribute("userSales",sales.getContent());
		model.addAttribute("page", sales);
		return "sale/shopping";
	}
	
	
	@RequestMapping("/sale/buy/{id}")
	public String getBuy(Model model,@PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		Sale sale = salesService.getSale(id);
		if(salesService.buy(user,sale)) {
			return "redirect:/sale/shopping";
		}
		
		model.addAttribute("sale", sale);
		model.addAttribute("user", user);
		
		return "/error/insuficientMoney";
	}
}
