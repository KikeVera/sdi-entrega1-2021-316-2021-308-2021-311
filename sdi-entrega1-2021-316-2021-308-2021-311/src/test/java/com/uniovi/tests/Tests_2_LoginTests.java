package com.uniovi.tests;




import org.junit.*;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;


import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;

import org.junit.runners.MethodSorters;


//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests_2_LoginTests {
	
	static String PathFirefox65= "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024= "C:\\Users\\Kike\\Desktop\\SDI\\geckoDriver\\geckodriver024win64.exe";
	
	static WebDriver driver= getDriver(PathFirefox65, Geckdriver024); 
	static String URL= "http://localhost:8090";
	
	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	
	
	//Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() throws Exception {
		driver.navigate().to(URL);
	}
	
	//Después de cadaprueba se borran las cookies del navegador
	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}
	
	
	
	//Al finalizar la última prueba 
	@AfterClass
	 static public void end() {
		//Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}
	
	// PR05. Inicio de sesión con datos válidos (administrador).
	@Test
	public void PR05() {
		// Nos logeamos como administrador y que comprobamos que aparecemos en la página
		// de este

		PO_PrivateView.loginAndCheckKey(driver, "admin@email.com", "admin", "administradorAutenticado.message",
				PO_Properties.getSPANISH());

	}

	// PR06.Inicio de sesión con datos válidos (usuario estándar).
	@Test
	public void PR06() {
		// Nos logeamos como usuario y que comprobamos que aparecemos en la página de
		// este

		PO_PrivateView.loginAndCheckKey(driver, "PedroDiaz@gmail.com", "123456", "usuarioAutenticado.message",
				PO_Properties.getSPANISH());

	}

	// PR07.Inicio de sesión con datos inválidos (usuario estándar, campo email y
	// contraseña vacíos).
	@Test
	public void PR07() {
		// Comprobamos que nos sale el mensae de error correspondiente
		PO_PrivateView.loginAndCheckKey(driver, "", "", "Error.empty", PO_Properties.getSPANISH());

	}

	// PR08. Inicio de sesión con datos válidos (usuario estándar, email existente,
	// pero contraseña incorrecta).
	@Test
	public void PR08() {
		// Comprobamos que nos sale el mensae de error correspondiente
		PO_PrivateView.loginAndCheckKey(driver, "PedroDiaz@gmail.com", "1234", "Error.signup.password.incorrect",
				PO_Properties.getSPANISH());

	}

	// PR09. Inicio de sesión con datos inválidos (usuario estándar, email no
	// existente en la aplicación)
	@Test
	public void PR09() {
		PO_PrivateView.loginAndCheckKey(driver, "Pedro@gmail.com", "1234", "Error.signup.email.notexist",
				PO_Properties.getSPANISH());

	}
	
	
	

}
