package com.uniovi.tests;




import org.junit.*;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;


import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_View;

import org.junit.runners.MethodSorters;


//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests_3_LogoutTests {
	
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

	// PR12. Hacer click en la opción de salir de sesión y comprobar que se redirige
	// a la página de inicio de sesión (Login).
	@Test
	public void PR010() {
		// Nos logeamos como usuario y que comprobamos que aparecemos en la página
		// de este

		PO_PrivateView.loginAndCheckKey(driver, "PedroDiaz@gmail.com", "123456", "usuarioAutenticado.message",
				PO_Properties.getSPANISH());
		//
		PO_PrivateView.clickOptionCheckKey(driver, "logout", "text", "identificate.title", PO_Properties.getSPANISH());

	}

	// PR11.Comprobar que el botón cerrar sesión no está visible si el usuario no
	// está autenticado.
	@Test
	public void PR011() {
		// Comprueba que el elemento logout no aparece al no estar logeado
		PO_View.checkNotElement(driver, "Logout");

	}
		
		
	
	

}
