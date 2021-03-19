package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Sale;
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
	public String getList(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		model.addAttribute("salesList", salesService.getSaleByUser(user));
		return "sale/list";
	}

	@RequestMapping(value = "/sale/add", method = RequestMethod.POST)
	public String setSales(@Validated Sale sale, BindingResult errors) {
		addSaleValidator.validate(sale, errors);
		if (errors.hasErrors()) {
			return "sale/add";
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
		salesService.deleteSale(id);
		return "redirect:/sale/list";
	}
}
