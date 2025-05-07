package com.example.subscription_service.repository;

import com.example.subscription_service.dto.TopSubscriptionDTO;
import com.example.subscription_service.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>, SubscriptionRepositoryCustom {
    List<Subscription> findByUserId(Long userId);

}