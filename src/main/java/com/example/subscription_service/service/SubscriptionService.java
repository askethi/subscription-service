package com.example.subscription_service.service;

import com.example.subscription_service.dto.TopSubscriptionDTO;
import com.example.subscription_service.entity.Subscription;
import com.example.subscription_service.exception.InvalidInputException;
import com.example.subscription_service.exception.ResourceNotFoundException;
import com.example.subscription_service.repository.SubscriptionRepository;
import com.example.subscription_service.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public Subscription addSubscription(Long userId, Subscription subscription) {
        var user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        subscription.setUser(user);
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getSubscriptions(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public void removeSubscription(Long userId, Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));
        if (!subscription.getUser().getId().equals(userId)) {
            throw new InvalidInputException("Subscription does not belong to this user");
        }

        subscriptionRepository.delete(subscription);
    }
    public List<TopSubscriptionDTO> findTopNSubscriptions(int n) {
        return subscriptionRepository.findTopNSubscriptions(n);
    }
}