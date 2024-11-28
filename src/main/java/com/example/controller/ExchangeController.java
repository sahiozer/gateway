package com.example.controller;

import com.example.dto.BankRate;
import com.example.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping("/gold/rates")
    public List<BankRate> getGoldRates() {
        return exchangeService.getGoldRates();
    }

    @GetMapping("/silver/rates")
    public List<BankRate> getSilverRates() {
        return exchangeService.getSilverRates();
    }

}
