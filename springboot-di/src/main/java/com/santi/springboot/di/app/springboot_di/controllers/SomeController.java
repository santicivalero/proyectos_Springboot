package com.santi.springboot.di.app.springboot_di.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santi.springboot.di.app.springboot_di.models.Product;
import com.santi.springboot.di.app.springboot_di.services.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class SomeController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> list() {

        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product show(@PathVariable Long id) {

        return productService.findById(id);
    }

}
