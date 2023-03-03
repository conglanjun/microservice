support grpc and rest http

http://localhost:8081/helloGrpc?name=clj to request client http controller, then invoke grpc server.

http://localhost:8082/helloRest?param=clj start a controller from grpc-server. so it can also support rest http.