package com.santi.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.santi.springboot.di.app.springboot_di.models.Product;
import com.santi.springboot.di.app.springboot_di.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${config.price.tax}")
    private Double tax;

    private ProductRepository repository;

    public ProductServiceImpl(@Qualifier("productJson") ProductRepository repository) {
        this.repository = repository;
    } // con el constructor el Autowired es innecesario indicarlo

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * tax;
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceTax.longValue()); // casteamos porque clone() devuelve
            // un Object
            return newProd;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }

}

// package com.santi.springboot.di.app.springboot_di.services;

// import java.util.List;
// import java.util.stream.Collectors;

// //import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.beans.factory.annotation.Value;
// //import org.springframework.core.env.Environment;
// import org.springframework.stereotype.Service;

// import com.santi.springboot.di.app.springboot_di.models.Product;
// import
// com.santi.springboot.di.app.springboot_di.repositories.ProductRepository;

// @Service
// public class ProductServiceImpl implements ProductService {

// // @Autowired
// // private Environment environment;

// @Value("${config.price.tax}")
// private Double tax;

// // @Autowired
// // @Qualifier("productFoo") si queremos elegir esta implementacion y estamos
// // inyectando por atributo
// private ProductRepository repository;

// public ProductServiceImpl(@Qualifier("productList") ProductRepository
// repository/* , Environment environment */) {
// // this.environment = environment;
// this.repository = repository;
// } // con el constructor el Autowired es innecesario indicarlo

// // private ProductRepositoryImpl repository = new ProductRepositoryImpl();
// como
// // estamos trabajando con inyeccion de dependencias, no es necesario crearlo
// // nosotros con el new, sino que el contenedor de Spring provee el objeto

// // @Autowired
// // public void setRepository(ProductRepository repository) {
// // this.repository = repository;
// // } //se puede usar el setter para inyectar tambien, con el autowired

// @Override
// public List<Product> findAll() {
// return repository.findAll().stream().map(p -> {
// // Double priceTax = p.getPrice() *
// environment.getProperty("config.price.tax",
// // Double.class);
// Double priceTax = p.getPrice() * tax;
// Product newProd = (Product) p.clone();
// newProd.setPrice(priceTax.longValue()); // casteamos porque clone() devuelve
// // un Object
// return newProd;
// }).collect(Collectors.toList());
// }

// // public List<Product> findAll() {
// // return repository.findAll().stream().map(p -> {
// // Double priceImp = p.getPrice() * 1.25d; // trabajamos con double xq es un
// // decimal, pero volvemos a long porque es el tipo del precio
// // p.setPrice(priceImp.longValue()); asi no porque si no modificamos el
// // precio del original, violando el principio de inmutabilidad
// // return p;
// // }).collect(Collectors.toList()); // transformar de nuevo a lista porque es
// un
// // stream
// // }// con @ResquestScope en el repositorio sí se pueden modificar los
// atributos
// // // del objeto original, porque el ambito es solo la peticion

// @Override
// public Product findById(Long id) {
// return repository.findById(id);
// }

// }