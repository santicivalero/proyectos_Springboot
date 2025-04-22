package com.santi.curso.springboot.webapp.springboot_web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santi.curso.springboot.webapp.springboot_web.models.User;
import com.santi.curso.springboot.webapp.springboot_web.models.dto.ParamDto;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    @Value("${config.username}")
    private String username;

    // @Value("${config.mensaje}")
    // private String mensaje;

    @Value("${config.listOfValues}")
    private String[] listOfValues;

    @Value("${config.code}")
    private Integer code;

    @Value("#{'${config.listOfValues}'.split(',')}")
    private List<String> valueList;

    @Value("#{'${config.listOfValues}'.toUpperCase()}")
    private String valueString;

    @Value("#{${config.valuesMap}}")
    private Map<String, Object> valuesMap;

    @Value("#{${config.valuesMap}.product}")
    private String product;

    @Value("#{${config.valuesMap}.price}")
    private Long price;

    @Autowired
    private Environment environment;

    @GetMapping("/baz/{message}")
    public ParamDto baz(@PathVariable String message) {

        ParamDto param = new ParamDto();
        param.setMessage(message);

        return param;
    }

    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id) {

        Map<String, Object> json = new HashMap<>();

        json.put("product", product);
        json.put("id", id);

        return json;

    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        // hacer algo con el usuario, como save en bdd
        user.setName(user.getName().toUpperCase());
        return user;

    }

    @GetMapping("/values")
    public Map<String, Object> values(@Value("${config.mensaje}") String mensaje) {

        Long code2 = environment.getProperty("config.code", Long.class);
        Map<String, Object> json = new HashMap<>();

        json.put("username", username);
        json.put("message", mensaje);
        json.put("message2", environment.getProperty("config.mensaje"));
        json.put("listOfValues", listOfValues);
        json.put("valueString", valueString);
        json.put("valueList", valueList);
        json.put("valueMap", valuesMap);
        json.put("product", product);
        json.put("price", price);
        json.put("code", code);
        // json.put("code2", Integer.valueOf(environment.getProperty("config.code")));
        json.put("code2", code2);

        return json;
    }

}
