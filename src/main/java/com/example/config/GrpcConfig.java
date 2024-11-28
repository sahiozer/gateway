package com.example.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shared.GoldServiceGrpc;
import shared.SilverServiceGrpc;

@Configuration
public class GrpcConfig {

    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
    }

    @Bean
    public GoldServiceGrpc.GoldServiceBlockingStub goldServiceStub(ManagedChannel managedChannel) {
        return GoldServiceGrpc.newBlockingStub(managedChannel);
    }

    @Bean
    public SilverServiceGrpc.SilverServiceBlockingStub exchangeServiceStub(ManagedChannel managedChannel) {
        return SilverServiceGrpc.newBlockingStub(managedChannel);
    }
}

