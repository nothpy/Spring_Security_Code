package com.formBasedAuthentication;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.formBasedAuthentication.dao.UserRepo;
import com.formBasedAuthentication.entity.User;

@SpringBootApplication
public class FormBasedAuthenticationApplication implements  CommandLineRunner{  // CommandLineRunner Automaticaly database me table create karta hai 

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	public static void main(String[] args) {
		SpringApplication.run(FormBasedAuthenticationApplication.class, args);
		
		System.out.println("Your File Code is Running...............................");
	}

	
	// ab user banane ke liye hume user ko store karana hoga database me lekin user ko db me store karne ke liye humre pass DAO hona chaiye .
	@Override
	public void run(String... args) throws Exception {
		System.out.println("मैं पहले चलूँगा______मैं पहले चलूँगा______मैं पहले चलूँगा______मैं पहले चलूँगा______मैं पहले चलूँगा______मैं पहले चलूँगा______मैं पहले चलूँगा ");
		
		User user1 =  new User("admin", bCryptPasswordEncoder.encode("admin1234"),"ROLE_ADMIN");
		User user2 =  new User("member", bCryptPasswordEncoder.encode("member1234"),"ROLE_MEMBER");
		
		List<User>list =  Arrays.asList(user1,user2);
		userRepo.saveAll(list);
		
		
	}
}
