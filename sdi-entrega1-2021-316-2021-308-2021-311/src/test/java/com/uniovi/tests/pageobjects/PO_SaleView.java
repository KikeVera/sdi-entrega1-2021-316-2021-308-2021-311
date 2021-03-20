package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_SaleView extends PO_NavView {
	
	static public void fillForm(WebDriver driver, String titlep,String descriptionp,String costp,boolean check) {
			WebElement title = driver.findElement(By.name("title"));
			title.click();
			title.clear();
			title.sendKeys(titlep);
			
			WebElement description = driver.findElement(By.name("description"));
			description.click();
			description.clear();
			description.sendKeys(descriptionp);
			
			WebElement cost = driver.findElement(By.name("cost"));
			cost.click();
			cost.clear();
			cost.sendKeys(costp);
			
			if(check) {
				By enlace = By.xpath("/html/body/div[2]/form/div[4]/div/input");
				driver.findElement(enlace).click();
			}
			
			
			//Pulsar el boton de Alta.
			By boton = By.className("btn");
			driver.findElement(boton).click();
		}


	

}
