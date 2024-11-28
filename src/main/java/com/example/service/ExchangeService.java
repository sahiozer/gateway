package com.example.service;

import com.example.dto.BankRate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shared.Exchange;
import shared.GoldServiceGrpc;
import shared.SilverServiceGrpc;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExchangeService {

    private final GoldServiceGrpc.GoldServiceBlockingStub goldServiceStub;
    private final SilverServiceGrpc.SilverServiceBlockingStub exchangeServiceStub;

    public List<BankRate> getGoldRates() {
        var response = goldServiceStub.getGoldRates(Exchange.EmptyRequest.newBuilder().build());

        return response.getRatesList().stream()
                .map(rate -> new BankRate(
                        rate.getBankName(),
                        rate.getBuyingRate(),
                        rate.getSellingRate()
                ))
                .collect(Collectors.toList());
    }

    public List<BankRate> getSilverRates() {
        var response = exchangeServiceStub.getSilverRates(Exchange.EmptyRequest.newBuilder().build());

        return response.getRatesList().stream()
                .map(rate -> new BankRate(
                        rate.getBankName(),
                        rate.getBuyingRate(),
                        rate.getSellingRate()
                ))
                .collect(Collectors.toList());
    }
}

