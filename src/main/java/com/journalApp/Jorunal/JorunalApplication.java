package com.journalApp.Jorunal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JorunalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JorunalApplication.class, args);
	}
}
