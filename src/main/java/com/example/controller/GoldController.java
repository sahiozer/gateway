package com.example.controller;

import com.example.dto.GoldRequest;
import org.springframework.web.bind.annotation.*;
import shared.Exchange;
import shared.ExchangeServiceGrpc;
import shared.GoldServiceGrpc;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/gold")
public class GoldController {


    private final GoldServiceGrpc.GoldServiceBlockingStub goldServiceStub;
    private final ExchangeServiceGrpc.ExchangeServiceBlockingStub exchangeServiceStub;

    public GoldController(GoldServiceGrpc.GoldServiceBlockingStub goldServiceStub, ExchangeServiceGrpc.ExchangeServiceBlockingStub exchangeServiceStub) {
        this.goldServiceStub = goldServiceStub;
        this.exchangeServiceStub = exchangeServiceStub;
    }

    @GetMapping("/api/gold/status")
    public String getStatus() {
        return "Gold Gateway is up and running!";
    }

    @PostMapping("/process")
    public String processGoldRequest(@RequestBody GoldRequest goldRequest) {
        Exchange.GoldPriceRequest grpcRequest = Exchange.GoldPriceRequest.newBuilder()
                .setCurrency(goldRequest.getCurrency())
                .build();

        // Call the gRPC service
        Exchange.GoldPriceResponse grpcResponse = goldServiceStub.getGoldPrice(grpcRequest);
        return "Gold price for " + goldRequest.getCurrency() + " is: " + grpcResponse.getPrice();
    }

    @GetMapping("/exchange/rates")
    public List<BankRate> getBankRates() {
        // Call the gRPC service
        Exchange.BankRatesResponse response = exchangeServiceStub.getBankRates(Exchange.EmptyRequest.newBuilder().build());

        return response.getRatesList().stream()
                .map(rate -> new BankRate(
                        rate.getBankName(),
                        rate.getGoldBuyingRate(),
                        rate.getGoldSellingRate()
                ))
                .collect(Collectors.toList());
    }

    public static class BankRate {
        private final String bankName;
        private final float goldBuyingRate;
        private final float goldSellingRate;

        public BankRate(String bankName, float goldBuyingRate, float goldSellingRate) {
            this.bankName = bankName;
            this.goldBuyingRate = goldBuyingRate;
            this.goldSellingRate = goldSellingRate;
        }

        public String getBankName() {
            return bankName;
        }

        public float getGoldBuyingRate() {
            return goldBuyingRate;
        }

        public float getGoldSellingRate() {
            return goldSellingRate;
        }
    }
}
