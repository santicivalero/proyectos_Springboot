package com.santi.curso.springboot.di.factura.springboot_difactura.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santi.curso.springboot.di.factura.springboot_difactura.models.Client;
import com.santi.curso.springboot.di.factura.springboot_difactura.models.Invoice;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private Invoice invoice;

    @GetMapping("/show")
    public Invoice show() {
        Invoice i = new Invoice();

        Client c = new Client();
        c.setLastname(invoice.getClient().getLastname());
        c.setName(invoice.getClient().getName());

        i.setClient(c);
        i.setDescription(invoice.getDescription());
        i.setItems(invoice.getItems());

        return i;
    }
    // para solucionar el problema de atributos basura
    // creamos un objeto real y devolvemos ese objeto con los datos del objeto proxy
    // tambien se podria usar un dto

    // @GetMapping("/show")
    // public Invoice show() {
    // return invoice;
    // } asi de simple se devuelve cuando no cambiamos los scopes

}
