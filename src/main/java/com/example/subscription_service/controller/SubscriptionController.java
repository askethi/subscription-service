package com.example.subscription_service.controller;

import com.example.subscription_service.dto.TopSubscriptionDTO;
import com.example.subscription_service.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/top")
    public List<TopSubscriptionDTO> getTopSubscriptions() {
        return subscriptionService.findTopNSubscriptions(3);
    }
}