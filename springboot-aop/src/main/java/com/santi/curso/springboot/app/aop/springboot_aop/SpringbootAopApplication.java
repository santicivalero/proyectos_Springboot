package com.santi.curso.springboot.app.aop.springboot_aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy // en últimas versiones de Spring no es necesario
@SpringBootApplication
public class SpringbootAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAopApplication.class, args);
	}

}
