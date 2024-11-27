package com.example.service;

import com.example.dto.GoldRequest;
import org.springframework.stereotype.Service;

@Service
public class GoldService {

    public String processGold(GoldRequest goldRequest) {
        // Example logic - you can add more business logic here
        return "Processed gold request for: " + goldRequest.getName();
    }
}
