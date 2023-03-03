package com.example.grpcserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/helloRest")
    public String hello(@RequestParam("param") String name) {
        System.out.println(name);
        return "hello " + name;
    }
}
