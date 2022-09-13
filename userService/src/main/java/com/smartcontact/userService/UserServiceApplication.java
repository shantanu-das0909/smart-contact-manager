package com.smartcontact.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.smartcontact.userService.model.User;
import com.smartcontact.userService.repository.UserRepository;

@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		User user1 = new User(1, "sayandeep", "Sayandeep Maitra", "sayandeep.maitra@gmail.com", "1234567890");
		User user2 = new User(2, "soumava", "Soumava Dutta", "soumava.dutta@gmail.com", "0987654321");
		User user3 = new User(3, "sayantan", "Sayantan Ghosh", "sayantan.ghosh@gmail.com", "15975384620");
		User user4 = new User(4, "shantanu", "Shantanu Das", "shantanu.das@gmail.com", "15975384620");
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);

	}

}
