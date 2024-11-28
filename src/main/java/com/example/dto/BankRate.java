package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankRate {
    private String bankName;
    private float goldBuyingRate;
    private float goldSellingRate;
}

