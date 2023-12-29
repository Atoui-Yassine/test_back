package com.example.test_technique;

import com.example.test_technique.models.user.ERole;
import com.example.test_technique.models.user.Role;
import com.example.test_technique.models.user.UserModel;
import com.example.test_technique.repositories.userR.RoleRepository;
import com.example.test_technique.repositories.userR.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TestTechniqueApplication {
	@Autowired
	PasswordEncoder encoder;
	public static void main(String[] args) {
		SpringApplication.run(TestTechniqueApplication.class, args);
	}


	@Bean
	CommandLineRunner run(RoleRepository roleRpository , UserRepository userRepository){
		return args -> {

			if (roleRpository.count()<1) {

				roleRpository.save(new Role( null,ERole.ROLE_ADMIN));
				roleRpository.save(new Role(null, ERole.ROLE_USER));

			}
			if(!userRepository.existsByEmail("Admin")){
				UserModel user = new UserModel(
						"Admin",
						"admin",
						encoder.encode("password")
				);

				Role userRole = roleRpository.findRoleByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));

				user.getRoles().add(userRole);
				userRepository.save(user);
			}
		};
	}
}