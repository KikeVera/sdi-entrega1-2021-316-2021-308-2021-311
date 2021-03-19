package com.uniovi.tests.pageobjects;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.uniovi.tests.util.SeleniumUtils;



public class PO_PrivateView extends PO_NavView {
	
	
	
	
	
	static public void loginAndCheck (WebDriver driver, String email,String password, String check) {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, email, password);
		// COmprobamos que entramos en la pagina privada del Profesor
		PO_View.checkElement(driver, "text", check);
		
		
	}
	
	static public void loginAndCheckKey (WebDriver driver, String email,String password, String check,int idioma) {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, email, password);
		// COmprobamos que entramos en la pagina privada del Profesor
		PO_View.checkKey(driver, check, idioma);
		
		
	}
	
	static public void deleteUsers(WebDriver driver,int ...filas) {
		
		List<String> emails=new ArrayList<>();
		for(int fila:filas) {
			By enlace = By.xpath("//table/tbody/tr["+fila+"]/td[4]/input");
		//Hacemos click en el check box
			driver.findElement(enlace).click();
		
		//Obtenemos el email de la fila del checkbox
			String email = driver.findElement( By.xpath("//table/tbody/tr["+fila+"]/td[1]")).getText();
			PO_View.checkElement(driver, "text",email);
			emails.add(email);
		}
		//Hacemos click en elminar
		By boton = By.id("btEliminar");
		driver.findElement(boton).click();
		
		for(String email:emails) {
		
			//Comprobamos que el email ya no está
			SeleniumUtils.textoNoPresentePagina(driver, email);
		}
		
		
		
	}
	
	
	
	
}
