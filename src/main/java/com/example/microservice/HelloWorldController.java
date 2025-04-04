package com.example.microservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    
    @GetMapping("/")
    public String root() {
        return "Server is running!";
    }
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}