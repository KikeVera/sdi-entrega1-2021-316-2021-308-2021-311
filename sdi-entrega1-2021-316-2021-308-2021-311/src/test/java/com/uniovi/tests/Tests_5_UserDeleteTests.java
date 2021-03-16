package com.uniovi.tests;




import java.util.List;

import org.junit.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_View;


import org.junit.runners.MethodSorters;


//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests_5_UserDeleteTests {
	
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
	
	// PR13. Ir a la lista de usuarios, borrar el primer usuario de la lista,
	// comprobar que la lista se actualiza y que el usuario desaparece
	@Test
	public void PR13() {
		// Nos logeamos como administrador y que comprobamos que aparecemos en la página
			// de este

			PO_PrivateView.loginAndCheckKey(driver, "admin@email.com", "admin", "administradorAutenticado.message",
					PO_Properties.getSPANISH());
				
			// Pinchamos en la opción de menu de Usuarios: //li[contains(@id, 'users-menu')]/a
			List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
			elementos.get(0).click();
				
			// Esperamos a aparezca la opción de ver usuarios: //a[contains(@href,
			// 'user/list')]
			elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
			// Pinchamos en listar usuarios.
			elementos.get(0).click();
				
			//Borramos el usuario de la primera fila
			PO_PrivateView.deleteUsers(driver, 1);
				
						
				

	}

	// PR14.Ir a la lista de usuarios, borrar el último usuario de la lista,
	// comprobar que la lista se actualiza y que el usuario desaparece
	@Test
	public void PR14() {
		// Nos logeamos como administrador y que comprobamos que aparecemos en la página
		// de este

		PO_PrivateView.loginAndCheckKey(driver, "admin@email.com", "admin", "administradorAutenticado.message",
				PO_Properties.getSPANISH());
		
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id, 'users-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		
		// Esperamos a aparezca la opción de ver usuarios: //a[contains(@href,
		// 'user/list')]
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
		// Pinchamos en listar usuarios.
		elementos.get(0).click();
		
		//Obtenemos el numero de filas
		int nFilas=PO_View.checkElement(driver, "free", "//tbody/tr").size();
		//Borramos el usuario de la ultima fila
		PO_PrivateView.deleteUsers(driver, nFilas);
		
	
		
		
	}

	// PR15.Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la lista se
	// actualiza y que losusuarios desaparecen.
	@Test
	public void PR15() {
		// Nos logeamos como administrador y que comprobamos que aparecemos en la página
			// de este

			PO_PrivateView.loginAndCheckKey(driver, "admin@email.com", "admin", "administradorAutenticado.message",
					PO_Properties.getSPANISH());
				
			// Pinchamos en la opción de menu de Usuarios: //li[contains(@id, 'users-menu')]/a
			List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
			elementos.get(0).click();
				
			// Esperamos a aparezca la opción de ver usuarios: //a[contains(@href,
			// 'user/list')]
			elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
			// Pinchamos en listar usuarios.
			elementos.get(0).click();
				
		
			//Borramos el usuario de la ultima fila
			PO_PrivateView.deleteUsers(driver, 2,3,4);

	}
	
	
	

}
