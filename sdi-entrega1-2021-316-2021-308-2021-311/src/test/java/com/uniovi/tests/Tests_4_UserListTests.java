package com.uniovi.tests;




import java.sql.SQLException;
import java.util.List;


import org.junit.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.repositories.UsersRepository;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.DatabaseUtils;

import org.junit.runners.MethodSorters;


//Ordenamos las pruebas por el nombre del método

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests_4_UserListTests {
	
	@Autowired
	UsersRepository usersRepository;
	
	
	
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

	// PR12. HMostrar el listado de usuarios y comprobar que se muestran to dos los
	// que existen en el sistema.
	@Test
	public void PR012() throws ClassNotFoundException, SQLException {
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
		
		
		for(String email:DatabaseUtils.getUsersEmail()) {
			
			PO_View.checkElement(driver, "text",email);
		}
			



	}
		

	

		
	
	
}

