package com.app.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.Project.model.User;
import com.app.Project.repository.RegistrationRepository;

@SuppressWarnings("unused")
@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
