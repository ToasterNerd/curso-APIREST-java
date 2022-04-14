package com.cursojava.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//esta anotacion de abajo sirve para indicarle al proyecto que se usa el framework de spring
@SpringBootApplication
public class CursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoApplication.class, args);
	}

}
