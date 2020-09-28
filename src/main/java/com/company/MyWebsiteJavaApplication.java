package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.company.registration.controller.RegistrationController;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MyWebsiteJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyWebsiteJavaApplication.class, args);
	}

}
