package com.ppinol.coworkingapp;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Controller {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
}
