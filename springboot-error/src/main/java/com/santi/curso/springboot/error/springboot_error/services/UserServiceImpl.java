package com.santi.curso.springboot.error.springboot_error.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santi.curso.springboot.error.springboot_error.models.domain.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private List<User> users;

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    // public Optional<User> findById(Long id) {
    // User user = null;
    // for (User u : users) {
    // if (u.getId().equals(id)) {
    // user = u;
    // break;
    //
    // }
    // // no usar == para la comparación porque compara por referencia, no por
    // valores
    // // Long es una clase objeto, no un tipo primitivo (long)
    // // la pregunta que hace == es: ¿Apuntan a la misma ubicación en memoria?
    // // y no: ¿Tienen el mismo valor? para eso esta equals()
    // return Optional.ofNullable(user); // si no encuentra el usuario, devuelve un
    // Optional.empty() (en vez de null)

    // }

}
