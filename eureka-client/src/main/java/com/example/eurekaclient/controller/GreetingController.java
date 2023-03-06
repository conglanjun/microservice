package com.example.eurekaclient.controller;

import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GreetingController {

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("/greeting")
    public String greeting() {
        log.info("greeting");
        return String.format("Hello from %s!", eurekaClient.getApplication(appName).getName());
    }

    @RequestMapping("/feignDemo")
    public String feignDemo() {
        log.info("feign demo request...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return String.format("demo feign %s!", eurekaClient.getApplication(appName).getName());
    }
}
