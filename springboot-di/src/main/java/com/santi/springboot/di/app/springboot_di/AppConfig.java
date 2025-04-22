package com.santi.springboot.di.app.springboot_di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.santi.springboot.di.app.springboot_di.repositories.ProductRepository;
import com.santi.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("classpath:json/products.json")
    private Resource resource;

    @Bean("productJson")
    ProductRepository productRepositoryJson() {
        // return new ProductRepositoryJson(); la clase tiene dos constructores
        return new ProductRepositoryJson(resource);
    }

}
