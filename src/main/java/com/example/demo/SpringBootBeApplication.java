package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
@SpringBootApplication
public class SpringBootBeApplication {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBeApplication.class, args);
	}

}
