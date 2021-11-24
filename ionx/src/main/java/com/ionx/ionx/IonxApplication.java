package com.ionx.ionx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ionx.ionx.domain.Positions;
import com.ionx.ionx.domain.Status;
import com.ionx.ionx.domain.User;
import com.ionx.ionx.repositories.PositionsRepository;
import com.ionx.ionx.repositories.StatusRepository;
import com.ionx.ionx.repositories.UserRepository;

@SpringBootApplication
public class IonxApplication  implements CommandLineRunner{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PositionsRepository positionRepository;
	
	@Autowired
	StatusRepository statusRepository;

	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		 now.format(formatter);
		SpringApplication.run(IonxApplication.class, args);
	}
	@Override
	public void run(String...args) throws Exception {
		List<Positions> positions = positionRepository.findAll();
		if (positions.isEmpty()) {
			positions.add(0, new Positions("Administrador"));
			positions.add(1, new Positions("Gerente"));
			positions.add(2, new Positions("Vendedor"));
			positions.add(3, new Positions("Indefinido"));
			
			positionRepository.saveAll(positions);
		}
		
		List<Status> status = statusRepository.findAll();
		if (status.isEmpty()) {
			status.add(0, new Status("Primeiro Contato"));
			status.add(1, new Status("Necessidades"));
			status.add(2, new Status("Proposta"));
			status.add(3, new Status("1ª Vistoria"));
			status.add(4, new Status("PROD. EQUIPAMENTO"));
			status.add(5, new Status("2ª VISTORIA"));
			status.add(6, new Status("IMPLANTAÇÃO"));
			status.add(7, new Status("CAPACITAÇÃO"));
			status.add(8, new Status("SUPORTE"));
			
			statusRepository.saveAll(status);
		}
		
		List<User> user = userRepository.findAll();
		if (user.isEmpty()) {
			User admin = new User();
			admin.setNome("admin");
			admin.setEmail("admin");
			admin.setSenha("admin");
			admin.setPosition(new Positions(1));
			userRepository.save(admin);
		}
	}

}
