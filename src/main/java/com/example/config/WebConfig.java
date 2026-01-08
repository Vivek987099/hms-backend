package com.example.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.user.repository.UserRepository;
import com.example.user.entity.User;


@Configuration
public class WebConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
	@Bean
	CommandLineRunner createAdmin(UserRepository userRepository) {
		return Args->{
			if(userRepository.count() ==0 ) {
				User admin= new User();
				admin.setUsername("admin@gmail.com");
				admin.setPassword(passwordEncoder().encode("admin"));
				admin.setRole("ADMIN");
				admin.setStatus(true);
				userRepository.save(admin);
				
				System.out.println("Admin created...");
			}
			
		};
	}

}
