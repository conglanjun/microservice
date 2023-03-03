package com.example.grpcclient.controller;

import com.clj.grpc.user.dto.AddUserRequest;
import com.clj.grpc.user.dto.UserResponse;
import com.clj.grpc.user.service.UserGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrpcDemoController {

    private static final Logger log = LoggerFactory.getLogger(GrpcDemoController.class);

    @GrpcClient("userDemoClient")
    private UserGrpc.UserBlockingStub blockingStub;

    @GetMapping("/helloGrpc")
    public String helloGrpc(@RequestParam("name") String name) {
        log.info("begin hello grpc");
        AddUserRequest addUserRequest = AddUserRequest.newBuilder().setName(name).setAge(11).setAddress("shenyang").build();
        UserResponse response = blockingStub.add(addUserRequest);
        log.info("add result, response:{}", response);
        return response.toString();
    }
}
