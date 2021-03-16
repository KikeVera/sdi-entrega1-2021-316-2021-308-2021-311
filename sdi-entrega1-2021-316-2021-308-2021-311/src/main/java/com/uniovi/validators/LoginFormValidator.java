package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;

@Component
public class LoginFormValidator implements Validator {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private SecurityService securityService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user= (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.empty");
		
		if(!user.getEmail().trim().isEmpty() && !user.getPassword().trim().isEmpty()) {
		
			if(usersService.getUserByEmail(user.getEmail()) == null) {
				errors.rejectValue("email", "Error.signup.email.notexist");
			}
		
			else {
		
				if(!securityService.canBeLogged(user.getEmail(),user.getPassword())) {
					errors.rejectValue("password", "Error.signup.password.incorrect");
				}
		
			}
		}
	}

}
