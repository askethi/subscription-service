package com.example.subscription_service.repository;

import com.example.subscription_service.dto.TopSubscriptionDTO;

import java.util.List;

public interface SubscriptionRepositoryCustom {
    List<TopSubscriptionDTO> findTopNSubscriptions(int n);
}