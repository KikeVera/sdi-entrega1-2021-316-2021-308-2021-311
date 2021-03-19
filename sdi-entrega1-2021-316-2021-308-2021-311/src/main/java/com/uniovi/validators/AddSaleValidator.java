package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Sale;

@Component
public class AddSaleValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Sale.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Sale sale = (Sale) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		if (sale.getTitle().length() < 3 || sale.getTitle().length() > 20) {
			errors.rejectValue("title", "Error.sale.title.range");
		}
		if (sale.getDescription().length() > 280) {
			errors.rejectValue("description", "Error.sale.description.length");
		}
		if (sale.getCost() <0) {
			errors.rejectValue("cost", "Error.sale.cost");
		}
	}

}
