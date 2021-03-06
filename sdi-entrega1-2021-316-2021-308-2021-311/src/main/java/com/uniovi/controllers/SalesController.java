package com.uniovi.controllers;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uniovi.entities.Sale;

import com.uniovi.entities.User;
import com.uniovi.services.SalesService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddSaleValidator;

@Controller
public class SalesController {

	//Inyección de servicios necesarios en este controlador
	@Autowired
	private SalesService salesService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private AddSaleValidator addSaleValidator;
	
	@Autowired
	private HttpSession httpSession;

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
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		sale.setUser(user);
		
		if(checkbox!=null) {
			sale.setOutstanding(true);
		}
		addSaleValidator.validate(sale, errors);
		if (errors.hasErrors()) {
			return "sale/add";
		}
	
		
		if(sale.isOutstanding()) {
			usersService.updateMoney(user, -20);
			httpSession.setAttribute("user",usersService.getUserByEmail(email));
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
	
	
	
	//Peticicón que te lleva a vista de compras
	@RequestMapping("/sale/shopping")
	public String getList(Model model,Pageable pageable, 
			@RequestParam(value = "", required = false) String searchText) {
		//Obtenemos el usario autenticado
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		//Definimos el objeto para la paginación
		Page<Sale> sales= new PageImpl<Sale>(new LinkedList<Sale>());
		
		
		if(searchText==null || searchText.isEmpty()) {
			//Si no hay texto de busqueda se llamará al método que devuelve todas las ofertas
			sales=salesService.getShopping(pageable);
		}
		
		else {
			//Si hay texto de bisqueda se filtrará por este texto
			sales=salesService.getShopping(pageable, searchText);
		}
		
		//Se añaden el usuario, las ofertas y la paginación al módelo
		model.addAttribute("userSales",sales.getContent());
		model.addAttribute("user", user);
		model.addAttribute("page", sales);
		return "sale/shopping";
	}
	
	
	@RequestMapping("/sale/buy/{id}")
	public String getBuy(@PathVariable Long id,RedirectAttributes redAtributes) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		Sale sale = salesService.getSale(id);
		
		if(sale.getUser().getEmail().equals(user.getEmail())) {
			redAtributes.addFlashAttribute("error1", "");
			if(!sale.isAvailable()) {
				redAtributes.addFlashAttribute("error2", "");
			}
			return "redirect:/sale/shopping";
		}
		
		if(!salesService.buy(user,sale)) {
			redAtributes.addFlashAttribute("error3", "");
			return "redirect:/sale/shopping";
		}
		
		httpSession.setAttribute("user",usersService.getUserByEmail(email));
		return "redirect:/sale/shopping";
	}
	
	//Petición queconvierte una oferta en destacada por el coste de 20 euros
	@RequestMapping("/sale/highlight/{id}")
	public String highlight(@PathVariable Long id,RedirectAttributes redAtributes) {
		
		//Obtenemos el usario autenticado
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		
		//Si no tenemos bastante dinero mandamos un mensaje de error al usuario
		if(user.getMoney()<20) {
			
			redAtributes.addFlashAttribute("error", "");
			return "redirect:/sale/list";
		}
		
		//Si tenemos el dinero convertimos la oferta en destacada y restamos el dinero al usario
		Sale sale=salesService.getSale(id);
		salesService.highlight(sale);
		usersService.updateMoney(user, -20);
		
		//Actualizamos la sesíon para mostrar el nuevo saldo
		httpSession.setAttribute("user",usersService.getUserByEmail(email));
		
		
		return "redirect:/sale/list";
	}
	
	
	//Petición queconvierte una oferta en no destacada devolviendo el los 20 ueors
	@RequestMapping("/sale/unhighlight/{id}")
	public String unhighlight(@PathVariable Long id,Model model) {
		
		//Obtenemos el usario autenticado
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		
		//Convertimos la oferta en no destacada y le devolvemos los 20 euros al usuario
		Sale sale=salesService.getSale(id);
		salesService.unhighlight(sale);
		usersService.updateMoney(user, 20);
		
		//Actualizamos la sesíon para mostrar el nuevo saldo
		httpSession.setAttribute("user",usersService.getUserByEmail(email));
		
		
		return "redirect:/sale/list";
	}
	@RequestMapping("/sale/user/buys")
	public String getList(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		model.addAttribute("userBuys", salesService.getUserBuys(user));
		
		return "user/userBuysList";
	}
}
