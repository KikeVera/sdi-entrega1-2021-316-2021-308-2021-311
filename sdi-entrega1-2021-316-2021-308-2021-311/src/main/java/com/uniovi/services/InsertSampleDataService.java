package com.uniovi.services;



import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private SalesService salesService;
	
	@Autowired
	private ConversationsService conversationsService;
	

	@PostConstruct
	public void init() {
		User user1= new User("PedroDiaz@gmail.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setMoney(800);
		user1.setRole(rolesService.getRoles()[0]);
		User user2= new User("LucasNuñez@gmail.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setMoney(500);
		user2.setRole(rolesService.getRoles()[0]);
		User user3= new User("MariaRodriquez@gmail.com", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setMoney(300);
		user3.setRole(rolesService.getRoles()[0]);
		User user4= new User("LuciaGonzalez@gmail.com", "Lucía", "González");
		user4.setPassword("123456");
		user4.setMoney(600);
		user4.setRole(rolesService.getRoles()[0]);
		User user5= new User("PabloVega@gmail.com", "Pablo", "Vega");
		user5.setPassword("123456");
		user5.setMoney(200);
		user5.setRole(rolesService.getRoles()[0]);
		User user6= new User("MartaSuarez@gmail.com", "Marta", "Suarez");
		user6.setPassword("123456");
		user6.setMoney(1000);
		user6.setRole(rolesService.getRoles()[0]);
		User user7= new User("AntonioMoran@gmail.com", "Antonio", "Morán");
		user7.setPassword("123456");
		user7.setMoney(30000);
		user7.setRole(rolesService.getRoles()[0]);
		User user8= new User("admin@email.com", "Administrador", "Administrador");
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
		Sale sale2 = new Sale("Bici de carretera", "Bici de degunda mano", 300, user2);
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
		Sale sale16= new Sale("Gato hidraulico", "Gato hidraulico", 44.5, user2);
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
		
		salesService.buy(user1, sale2);
		salesService.buy(user1, sale4);
		salesService.buy(user2, sale1);
		salesService.buy(user2, sale5);
		salesService.buy(user3, sale6);
		salesService.buy(user3, sale7);
		salesService.buy(user4, sale9);
		salesService.buy(user4, sale13);
		salesService.buy(user5, sale14);
		salesService.buy(user5, sale16);
		salesService.buy(user6, sale3);
		salesService.buy(user6, sale8);
		salesService.buy(user7, sale15);
		salesService.buy(user7, sale11);
		
		Conversation conversacion1= new Conversation(user2, sale1);
		conversacion1.AddMessage(new Message(user2, "Hola"));
		conversacion1.AddMessage(new Message(user1, "Hola que tal"));
		Conversation conversacion2= new Conversation(user3, sale2);
		conversacion2.AddMessage(new Message(user3, "Hola"));
		conversacion2.AddMessage(new Message(user2, "Hola que tal"));
		Conversation conversacion3= new Conversation(user4, sale3);
		conversacion3.AddMessage(new Message(user4, "Hola"));
		conversacion3.AddMessage(new Message(user3, "Hola que tal"));
		Conversation conversacion4= new Conversation(user5, sale4);
		conversacion4.AddMessage(new Message(user5, "Hola"));
		conversacion4.AddMessage(new Message(user4, "Hola que tal"));
		Conversation conversacion5= new Conversation(user6, sale5);
		conversacion5.AddMessage(new Message(user6, "Hola"));
		conversacion5.AddMessage(new Message(user5, "Hola que tal"));
		Conversation conversacion6= new Conversation(user7, sale6);
		conversacion6.AddMessage(new Message(user7, "Hola"));
		conversacion6.AddMessage(new Message(user6, "Hola que tal"));
		Conversation conversacion7= new Conversation(user1, sale7);
		conversacion7.AddMessage(new Message(user1, "Hola"));
		conversacion7.AddMessage(new Message(user7, "Hola que tal"));
		Conversation conversacion8= new Conversation(user2, sale8);
		conversacion8.AddMessage(new Message(user2, "Hola"));
		conversacion8.AddMessage(new Message(user1, "Hola que tal"));
		Conversation conversacion9= new Conversation(user3, sale9);
		conversacion9.AddMessage(new Message(user3, "Hola"));
		conversacion9.AddMessage(new Message(user2, "Hola que tal"));
		Conversation conversacion10= new Conversation(user4, sale10);
		conversacion10.AddMessage(new Message(user4, "Hola"));
		conversacion10.AddMessage(new Message(user3, "Hola que tal"));
		Conversation conversacion11= new Conversation(user5, sale11);
		conversacion11.AddMessage(new Message(user5, "Hola"));
		conversacion11.AddMessage(new Message(user4, "Hola que tal"));
		Conversation conversacion12= new Conversation(user6, sale12);
		conversacion12.AddMessage(new Message(user6, "Hola"));
		conversacion12.AddMessage(new Message(user5, "Hola que tal"));
		Conversation conversacion13= new Conversation(user7, sale13);
		conversacion13.AddMessage(new Message(user7, "Hola"));
		conversacion13.AddMessage(new Message(user6, "Hola que tal"));
		Conversation conversacion14= new Conversation(user1, sale14);
		conversacion14.AddMessage(new Message(user1, "Hola"));
		conversacion14.AddMessage(new Message(user7, "Hola que tal"));
		Conversation conversacion15= new Conversation(user2, sale15);
		conversacion15.AddMessage(new Message(user2, "Hola"));
		conversacion15.AddMessage(new Message(user1, "Hola que tal"));
		Conversation conversacion16= new Conversation(user3, sale16);
		conversacion16.AddMessage(new Message(user3, "Hola"));
		conversacion16.AddMessage(new Message(user2, "Hola que tal"));
		Conversation conversacion17= new Conversation(user4, sale17);
		conversacion17.AddMessage(new Message(user4, "Hola"));
		conversacion17.AddMessage(new Message(user3, "Hola que tal"));
		Conversation conversacion18= new Conversation(user5, sale18);
		conversacion18.AddMessage(new Message(user5, "Hola"));
		conversacion18.AddMessage(new Message(user4, "Hola que tal"));
		Conversation conversacion19= new Conversation(user6, sale19);
		conversacion19.AddMessage(new Message(user6, "Hola"));
		conversacion19.AddMessage(new Message(user5, "Hola que tal"));
		Conversation conversacion20= new Conversation(user7, sale20);
		conversacion20.AddMessage(new Message(user7, "Hola"));
		conversacion10.AddMessage(new Message(user6, "Hola que tal"));
		Conversation conversacion21= new Conversation(user1, sale21);
		conversacion21.AddMessage(new Message(user1, "Hola"));
		conversacion21.AddMessage(new Message(user7, "Hola que tal"));
		
		conversationsService.addConversation(conversacion1);
		conversationsService.addConversation(conversacion2);
		conversationsService.addConversation(conversacion3);
		conversationsService.addConversation(conversacion4);
		conversationsService.addConversation(conversacion5);
		conversationsService.addConversation(conversacion6);
		conversationsService.addConversation(conversacion7);
		conversationsService.addConversation(conversacion8);
		conversationsService.addConversation(conversacion9);
		conversationsService.addConversation(conversacion10);
		conversationsService.addConversation(conversacion11);
		conversationsService.addConversation(conversacion12);
		conversationsService.addConversation(conversacion13);
		conversationsService.addConversation(conversacion14);
		conversationsService.addConversation(conversacion15);
		conversationsService.addConversation(conversacion16);
		conversationsService.addConversation(conversacion17);
		conversationsService.addConversation(conversacion18);
		conversationsService.addConversation(conversacion19);
		conversationsService.addConversation(conversacion20);
		conversationsService.addConversation(conversacion21);
		
		
		
	
		
		
		
		
		
		
		
	}

}
