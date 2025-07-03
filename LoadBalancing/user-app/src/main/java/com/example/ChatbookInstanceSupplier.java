package com.example;



import java.net.URI;
import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class ChatbookInstanceSupplier {

    @Bean
    public ServiceInstanceListSupplier chatbookSupplier() {
        return new ServiceInstanceListSupplier() {
            @Override
            public String getServiceId() {
                return "chatbook";
            }

            @Override
            public Flux<List<ServiceInstance>> get() {
                List<ServiceInstance> instances = List.of(
                    new DefaultServiceInstance("chatbook1", "chatbook", "localhost", 8081, false),
                    new DefaultServiceInstance("chatbook2", "chatbook", "localhost", 8082, false),
                    new DefaultServiceInstance("chatbook3", "chatbook", "localhost", 8083, false)
                );
                return Flux.just(instances);
            }
        };
    }
}
