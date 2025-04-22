package com.santi.curso.springboot.webapp.springboot_web.controllers;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santi.curso.springboot.webapp.springboot_web.models.User;
import com.santi.curso.springboot.webapp.springboot_web.models.dto.UserDto;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping("/details")
    public UserDto details() {

        UserDto userDto = new UserDto();
        User user = new User("Santiago", "Civalero");

        userDto.setUser(user);
        userDto.setTitle("Hola Mundo Spring Boot");

        return userDto;
    }

    @GetMapping("/list")
    public List<User> list() {
        User user = new User("Santiago", "Civalero");
        User user2 = new User("Andrés", "Guzmán");
        User user3 = new User("John", "Doe");

        // List<User> users = Arrays.asList(user, user2); //esta forma es más sencila
        // pero no permite agregar ni remover elementos, porque es una estructura fija
        // para eso hay que hacer:
        // List<User> users = new ArrayList<>(Arrays.asList(user, user2, user3));

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        users.add(user3);

        return users; // esta lógica de negocio no se maneja en el controlador, sino en un service o
                      // repository
                      // después lo migramos
    }

    @GetMapping("/details-map")
    public Map<String, Object> detailsMap() {

        User user = new User("Santiago", "Civalero");
        Map<String, Object> body = new HashMap<>();

        body.put("title", "Hola Mundo Spring Boot");
        body.put("user", user);

        return body;
    }

}
