package com.santi.springboot.di.app.springboot_di.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

//import com.fasterxml.jackson.core.exc.StreamReadException;
// import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santi.springboot.di.app.springboot_di.models.Product;

public class ProductRepositoryJson implements ProductRepository {

    private List<Product> list;

    public ProductRepositoryJson() { // forma mas programática
        Resource resource = new ClassPathResource("json/products.json");
        readValueJson(resource);
    }

    public ProductRepositoryJson(Resource resource) { // forma mas declarativa
        readValueJson(resource);
    }

    private void readValueJson(Resource resource) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            list = Arrays.asList(objectMapper.readValue(resource.getFile(), Product[].class));
            // } catch (StreamReadException e) { //getInputStream() tambien funciona
            // e.printStackTrace();
            // } catch (DatabindException e) {
            // e.printStackTrace(); // con IOException esta bien
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(Long id) {
        return list.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }

}
