package com.santi.curso.springboot.webapp.springboot_web.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.santi.curso.springboot.webapp.springboot_web.models.User;

@Controller
public class UserController {

    @GetMapping("/details")
    public String details(Model model) {
        User user = new User("Santiago", "Civalero");
        user.setEmail("santiagocivalero@hotmail.com");

        model.addAttribute("title", "Hola Mundo Spring Boot");
        model.addAttribute("user", user);

        return "details";
    }

    @GetMapping("/list")
    public String list(ModelMap model) {
        // List<User> users = Arrays.asList(
        // new User("Santiago", "Civalero"),
        // new User("Andrés", "Guzmán", "andresguzman@hotmail"),
        // new User("John", "Doe", "johndoe@hotmail"),
        // new User("Jane", "Doe"));

        // model.addAttribute("users", users); esta lista la estoy manejando con
        // ModelAttribute
        model.addAttribute("title", "Listado de usuarios!");

        return "list";
    }

    @ModelAttribute("users")
    public List<User> usersModel() {
        return Arrays.asList(
                new User("Santiago", "Civalero"),
                new User("Andrés", "Guzmán", "andresguzman@hotmail"),
                new User("John", "Doe", "johndoe@hotmail"),
                new User("Jane", "Doe"));
    }
}
