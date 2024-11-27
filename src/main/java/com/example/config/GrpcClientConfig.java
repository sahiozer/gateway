package com.example.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shared.ExchangeServiceGrpc;
import shared.GoldServiceGrpc;

@Configuration
public class GrpcClientConfig {

    @Bean
    public ManagedChannel grpcChannel() {
        // Replace "localhost" and 9090 with your gRPC server's host and port
        return ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext() // No SSL for local development
                .build();
    }

    @Bean
    public GoldServiceGrpc.GoldServiceBlockingStub goldServiceBlockingStub(ManagedChannel grpcChannel) {
        return GoldServiceGrpc.newBlockingStub(grpcChannel);
    }

    @Bean
    public ExchangeServiceGrpc.ExchangeServiceBlockingStub exchangeServiceBlockingStub(ManagedChannel exchangeServiceChannel) {
        return ExchangeServiceGrpc.newBlockingStub(exchangeServiceChannel);
    }
}

