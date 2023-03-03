package com.example.grpcserver.service;

import com.clj.grpc.user.dto.AddUserRequest;
import com.clj.grpc.user.dto.SearchUserRequest;
import com.clj.grpc.user.dto.UserResponse;
import com.clj.grpc.user.service.UserGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class UserServiceGrpcImpl extends UserGrpc.UserImplBase {

    private static final Logger log = LoggerFactory.getLogger(UserServiceGrpcImpl.class);

    @Override
    public void add(AddUserRequest request, StreamObserver<UserResponse> responseObserver) {
        log.info("name:{}, age:{}, address:{}", request.getName(), request.getAge(), request.getAddress());
        UserResponse userResponse = UserResponse.newBuilder().setId(1).setName(request.getName()).setAge(request.getAge()).setAddress(request.getAddress()).build();
        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
        log.info("finished add!");
    }

    @Override
    public void list(SearchUserRequest request, StreamObserver<UserResponse> responseObserver) {
        for (int i = 0; i < 3; i++) {
            UserResponse userResponse = UserResponse.newBuilder().setId(i + 1).setName(request.getName()).setAge(request.getAge()).setAddress(request.getAddress()).build();
            responseObserver.onNext(userResponse);
        }
        responseObserver.onCompleted();
    }
}
