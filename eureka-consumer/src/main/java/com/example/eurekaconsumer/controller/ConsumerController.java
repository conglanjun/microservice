package com.example.eurekaconsumer.controller;

import com.example.eurekaconsumer.feignClient.StoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient client;

    @Autowired
    private StoreClient storeClient;

    @GetMapping("/consumer")
    public String consumer() {
        log.info("consumer");
        List<ServiceInstance> instances = client.getInstances("spring-cloud-eureka-client1");
        ServiceInstance instance = instances.get(0);
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/greeting";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/store")
    public String greeting() {
        log.info("store");
        return storeClient.feignDemo();
    }
}
