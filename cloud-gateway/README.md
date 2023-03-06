1. start eureka server
2. start eureka-client and eureka-consumer, cloud-gateway
3. request gateway endpoint
you can also use java code configure it.
```java
@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/employee/**")
                        .uri("http://localhost:8081/")
                        .id("employeeModule"))

                .route(r -> r.path("/consumer/**")
                        .uri("http://localhost:8082/")
                        .id("consumerModule"))
                .build();
    }

}
```