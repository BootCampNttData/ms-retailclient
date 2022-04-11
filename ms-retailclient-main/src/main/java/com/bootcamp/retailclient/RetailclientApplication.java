package com.bootcamp.retailclient;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.bootcamp.retailclient.model.RetailClient;
import com.bootcamp.retailclient.repository.RetailClientRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class RetailclientApplication implements CommandLineRunner {
	
	/*SE INYECTA INTERFAZ REPOSITORY*/
	@Autowired
	private RetailClientRepository dao;
	
	/*SE INYECTA PARA TRABAJAR CON EL FLUJO DE TRABAJO DE MONGODB*/
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RetailclientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*SE DROPEA COLECCION PARA EVITAR DATOS REPETIDOS*/
		mongoTemplate.dropCollection("retailclient")
		.subscribe();
		
		Flux.just(new RetailClient("Juan","Alejo","74589814","DNI","CALLE 123","998885509","Casado","Masculino"),
				new RetailClient("Jaison","Juárez","45636693","DNI","CALLE 456","987958800","Soltero","Masculino"),
				new RetailClient("Roberto","Vargas","41236587","DNI","CALLE 587","965874411","Casado","Masculino"),
				new RetailClient("Maria","Villa","41256398","DNI","CALLE 478","965741188","Casada","Femenino"),
				new RetailClient("Aquiles","Casas","24785569","DNI","CALLE 999","954127799","Soltero","Masculino"),
				new RetailClient("Javiera","Abue","74589814","DNI","CALLE 123","998885509","Casada","Femenino"),
				new RetailClient("Veronica","Sillas","74589814","DNI","CALLE 123","998885509","Soltera","Femenino"),
				new RetailClient("Carlos","Marin","74589814","DNI","CALLE 123","998885509","Soltero","Masculino"),
				new RetailClient("Jorg","Saenz","74589814","DNI","CALLE 123","998885509","Casado","Masculino")
				)
		.flatMap(retclnt -> dao.save(retclnt))
		.subscribe();	
		
	}

}
