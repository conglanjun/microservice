package com.example.eurekaconsumer.feignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "spring-cloud-eureka-client1")
public interface StoreClient {
    @RequestMapping(method = RequestMethod.GET, value = "/feignDemo")
    @CircuitBreaker(name = "spring-cloud-eureka-client1", fallbackMethod = "getStoreFallback")
    String feignDemo();

    // if eureka client is closed, this method will be invoked.
    default String getStoreFallback(Exception exc) {
        return "exception:" + exc.getMessage();
    }
}
