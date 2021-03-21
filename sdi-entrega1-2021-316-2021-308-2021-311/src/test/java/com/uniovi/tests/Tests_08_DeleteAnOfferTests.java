package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.RolesService;
import com.uniovi.services.SalesService;
import com.uniovi.services.UsersService;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests_08_DeleteAnOfferTests {
	@Autowired
	private UsersService usersService;
	@Autowired
	private RolesService rolesService;

	@Autowired
	private SalesService salesService;

	@Autowired
	private UsersRepository usersRepository;

	
	static WebDriver driver = getDriver(PathTests.PathFirefox65, PathTests.Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() throws Exception {
		init();
		driver.navigate().to(URL);

	}

	// Después de cadaprueba se borran las cookies del navegador
	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();

	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// PR19. Ir a la lista de ofertas, borrar la primera oferta de la lista, comprobar que la lista se actualiza y
	//que la oferta desaparece.
	@Test
	public void PR19() {
		// Nos logeamos como usuario y que comprobamos que aparecemos en la página
		// de este

		PO_PrivateView.loginAndCheckKey(driver, "PedroDiaz@gmail.com", "123456", "usuarioAutenticado.message",
				PO_Properties.getSPANISH());

		// Pinchamos en la opción de menu de oferta: //li[contains(@id, 'sale-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'sale-menu')]/a");
		elementos.get(0).click();

		// Esperamos a aparezca la opción de listar nuestras ofertas:
		// //a[contains(@href,
		// '/sale/list')]
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/sale/list')]");
		// Pinchamos en listar ofertas de usuario.
		elementos.get(0).click();

		// Contamos el número de filas de ofertas
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 3);

		//Obtenemos el enlace para eliminar la primera oferta
		elementos = PO_View.checkElement(driver, "free", "//td[contains(text(), 'Bici de montaña')]/following-sibling::*/a[contains(@href, 'sale/delete')]");
		
		//Eliminamos la primera oferta
		elementos.get(0).click();
		
		// Contamos el número de filas de ofertas
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 2);
		
		//Comprobamos que no aparece la oferta
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Bici de montaña",PO_View.getTimeout() );
		
		// Cerrar sesion
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}
	
	// PR20. Ir a la lista de ofertas, borrar la última oferta de la lista, comprobar que la lista se actualiza y
	//que la oferta desaparece.
		@Test
		public void PR20() {
			// Nos logeamos como usuario y que comprobamos que aparecemos en la página
			// de este

			PO_PrivateView.loginAndCheckKey(driver, "LucasNuñez@gmail.com", "123456", "usuarioAutenticado.message",
					PO_Properties.getSPANISH());

			// Pinchamos en la opción de menu de oferta: //li[contains(@id, 'sale-menu')]/a
			List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'sale-menu')]/a");
			elementos.get(0).click();

			// Esperamos a aparezca la opción de listar nuestras ofertas:
			// //a[contains(@href,
			// '/sale/list')]
			elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/sale/list')]");
			// Pinchamos en listar ofertas de usuario.
			elementos.get(0).click();

			// Contamos el número de filas de ofertas
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
			assertTrue(elementos.size() == 3);

			//Obtenemos el enlace para eliminar la ultima oferta
			elementos = PO_View.checkElement(driver, "free", "//td[contains(text(), 'Bici de segunda mano')]/following-sibling::*/a[contains(@href, 'sale/delete')]");
			
			//Eliminamos la ultima oferta
			elementos.get(0).click();
			
			// Contamos el número de filas de ofertas
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
			assertTrue(elementos.size() == 2);
			
			//Comprobamos que no aparece la oferta
			SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Bici de segunda mano",PO_View.getTimeout() );
			
			// Cerrar sesion
			PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
		}

	public void init() {
		usersRepository.deleteAll();
		User user1 = new User("PedroDiaz@gmail.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setMoney(100);
		user1.setRole(rolesService.getRoles()[0]);
		User user2 = new User("LucasNuñez@gmail.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setMoney(100);
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("MariaRodriquez@gmail.com", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setMoney(100);
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("LuciaGonzalez@gmail.com", "Lucía", "González");
		user4.setPassword("123456");
		user4.setMoney(100);
		user4.setRole(rolesService.getRoles()[0]);
		User user5 = new User("PabloVega@gmail.com", "Pablo", "Vega");
		user5.setPassword("123456");
		user5.setMoney(100);
		user5.setRole(rolesService.getRoles()[0]);
		User user6 = new User("MartaSuarez@gmail.com", "Marta", "Suarez");
		user6.setPassword("123456");
		user6.setMoney(100);
		user6.setRole(rolesService.getRoles()[0]);
		User user7 = new User("AntonioMoran@gmail.com", "Antonio", "Morán");
		user7.setPassword("123456");
		user7.setMoney(100);
		user7.setRole(rolesService.getRoles()[0]);
		User user8 = new User("admin@email.com", "Administrador", "Administrador");
		user8.setMoney(0);
		user8.setPassword("admin");
		user8.setRole(rolesService.getRoles()[1]);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
		usersService.addUser(user8);

		Sale sale1 = new Sale("Bici de montaña", "Bici de degunda mano", 200.50, user1);
		Sale sale2 = new Sale("Bici de carretera", "Bici de segunda mano", 300, user2);
		Sale sale3 = new Sale("Ordenador portatil HP", "Portatil", 400, user3);
		Sale sale4 = new Sale("Ordenador de sobremesa", "PC viejo", 150, user4);
		Sale sale5 = new Sale("Nevera con congelador", "Nevera", 80, user5);
		Sale sale6 = new Sale("Ruedas de bici", "Ruedas de bici antipinchazo", 30, user6);
		Sale sale7 = new Sale("Juego de mesa", "Juego de mesa del monoply", 150, user7);
		Sale sale8 = new Sale("Mesa de salón", "Mesa de roble", 50.25, user1);
		Sale sale9 = new Sale("Telescopio", "Telescopio", 200, user2);
		Sale sale10 = new Sale("Gafas de buceo", "Gafas de buceo", 10, user3);
		Sale sale11 = new Sale("Gafas de cerca", "Gafas para leer de cerca", 50, user4);
		Sale sale12 = new Sale("Gafas de sol", "Gafas", 30, user5);
		Sale sale13 = new Sale("Desbrozadora", "Desbrozadora", 95.5, user6);
		Sale sale14 = new Sale("Barco de playmobil", "Barco pirata de playmovil", 60, user7);
		Sale sale15 = new Sale("Barco de pesca", "Barco de pesca como nuevo", 20000, user1);
		Sale sale16 = new Sale("Gato hidraulico", "Gato hidraulico", 44.5, user2);
		Sale sale17 = new Sale("Rascador para gatos", "Rascador", 15, user3);
		Sale sale18 = new Sale("Castillo para gatos", "Castillo grande para gatos", 100, user4);
		Sale sale19 = new Sale("Coche de playmobil", "Coche de policia de playmobil", 27.8, user5);
		Sale sale20 = new Sale("Helicoptero de playmobil", "Desbrozadora", 40.5, user6);
		Sale sale21 = new Sale("Helicoptero", "Helicoptero", 135000, user7);

		sale3.setOutstanding(true);
		sale15.setOutstanding(true);
		sale21.setOutstanding(true);

		salesService.addSale(sale1);
		salesService.addSale(sale2);
		salesService.addSale(sale3);
		salesService.addSale(sale4);
		salesService.addSale(sale5);
		salesService.addSale(sale6);
		salesService.addSale(sale7);
		salesService.addSale(sale8);
		salesService.addSale(sale9);
		salesService.addSale(sale10);
		salesService.addSale(sale11);
		salesService.addSale(sale12);
		salesService.addSale(sale13);
		salesService.addSale(sale14);
		salesService.addSale(sale15);
		salesService.addSale(sale16);
		salesService.addSale(sale17);
		salesService.addSale(sale18);
		salesService.addSale(sale19);
		salesService.addSale(sale20);
		salesService.addSale(sale21);

	}
}