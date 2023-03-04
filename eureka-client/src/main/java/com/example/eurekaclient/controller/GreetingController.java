package com.example.eurekaclient.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("/greeting")
    public String greeting() {
        return String.format("Hello from %s!", eurekaClient.getApplication(appName).getName());
    }

    @RequestMapping("/feignDemo")
    public String feignDemo() {
        return String.format("demo feign %s!", eurekaClient.getApplication(appName).getName());
    }
}
