package com.example.eurekaconsumer.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("spring-cloud-eureka-client1")
public interface StoreClient {
    @RequestMapping(method = RequestMethod.GET, value = "/feignDemo")
    String feignDemo();
}
