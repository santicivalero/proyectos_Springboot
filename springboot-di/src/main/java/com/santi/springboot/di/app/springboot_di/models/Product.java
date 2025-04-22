package com.santi.springboot.di.app.springboot_di.models;

public class Product implements Cloneable {

    private Long id;
    private String name;
    private Long price;

    public Product(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public Object clone() { // cambiar el protected por public
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new Product(this.getId(), this.getName(), this.getPrice()); // new Product(id, name, price) tambien
                                                                               // es correcto, si no se usa logica en
                                                                               // los getters, y se puede omitir el this
                                                                               // xq java sabe que se refiere al metodo
                                                                               // del objeto actual;
        }
    }

}
