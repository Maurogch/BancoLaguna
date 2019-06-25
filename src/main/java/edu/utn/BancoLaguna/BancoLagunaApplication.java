package edu.utn.BancoLaguna;

import edu.utn.BancoLaguna.model.Client;
import edu.utn.BancoLaguna.repository.ClientRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BancoLagunaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoLagunaApplication.class, args);
	}

	/*@Bean
	ApplicationRunner init(ClientRepository clientRepository) {
		return args -> {
			Client client = new Client("Cliente1", "UserCliente1", "pass");
			Client client2 = new Client("Cliente2", "UserCliente2", "pass");
			clientRepository.save(client);
			clientRepository.save(client2);
		};
	}*/

}
