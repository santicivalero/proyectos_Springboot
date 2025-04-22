package com.santi.curso.springboot.error.springboot_error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.santi.curso.springboot.error.springboot_error.models.domain.User;

@Configuration
public class AppConfig {

    @Bean
    List<User> users() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Pepe", "González"));
        users.add(new User(2L, "Andrés", "Mena"));
        users.add(new User(3L, "María", "Pérez"));
        users.add(new User(4L, "Josefa", "Ramírez"));
        users.add(new User(5L, "Ale", "Gutiérrez"));
        return users;
    }

}
