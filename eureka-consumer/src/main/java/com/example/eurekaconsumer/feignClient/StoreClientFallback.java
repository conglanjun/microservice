package com.example.eurekaconsumer.feignClient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StoreClientFallback implements FallbackFactory<StoreClient> {
    @Override
    public StoreClient create(Throwable cause) {
        return () -> "fall back consumer store client!";
    }
}
