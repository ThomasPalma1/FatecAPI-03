package com.ionx.ionx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ionx.ionx.domain.User;
import com.ionx.ionx.repositories.UserRepository;

@SpringBootApplication
public class IonxApplication  implements CommandLineRunner{
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(IonxApplication.class, args);
	}
	@Override
	public void run(String...args) throws Exception {
		List<User> user = userRepository.findAll();
		if (user.isEmpty()) {
			User admin = new User();
			admin.setEmail("admin");
			admin.setSenha("admin");
			admin.setTipo("admin");
			userRepository.save(admin);
		}
	}

}
