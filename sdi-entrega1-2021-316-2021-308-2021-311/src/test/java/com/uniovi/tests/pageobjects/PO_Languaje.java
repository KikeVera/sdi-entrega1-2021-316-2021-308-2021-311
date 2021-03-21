package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

public class PO_Languaje {
	
public static void checkChangeIdiom(WebDriver driver,String mensajeEs,String mensajeEn) {
		
	//Comprobamos el texto en español
	PO_View.checkElement(driver, "text", mensajeEs);
	
	//Cambiamos a inglés
	PO_NavView.changeIdiom(driver, "btnEnglish");
	//Comprobamos el texto en inglés
	PO_View.checkElement(driver, "text", mensajeEn);
	//Cambiamos de vuelta a español
	PO_NavView.changeIdiom(driver, "btnSpanish");
	//Comprobamos el texto en español
	PO_View.checkElement(driver, "text", mensajeEs);
	
		
		
		
		
		
	}
	

}
