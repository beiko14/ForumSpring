package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ForumSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumSpringApplication.class, args);
	}

}
