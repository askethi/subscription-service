package com.example.subscription_service.service;

import com.example.subscription_service.dto.DtoMapper;
import com.example.subscription_service.dto.SubscriptionRequest;
import com.example.subscription_service.dto.SubscriptionResponse;
import com.example.subscription_service.dto.TopSubscriptionDTO;
import com.example.subscription_service.entity.ServiceName;
import com.example.subscription_service.entity.Subscription;
import com.example.subscription_service.entity.User;
import com.example.subscription_service.exception.InvalidInputException;
import com.example.subscription_service.exception.ResourceNotFoundException;
import com.example.subscription_service.repository.SubscriptionRepository;
import com.example.subscription_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final DtoMapper dtoMapper;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository, DtoMapper dtoMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.dtoMapper = dtoMapper;
    }

    public SubscriptionResponse addSubscription(Long userId, SubscriptionRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        try {
            subscription.setServiceName(ServiceName.valueOf(request.getServiceName()));
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Invalid service name: " + request.getServiceName());
        }
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(LocalDateTime.now().plusMonths(1));
        Subscription saved = subscriptionRepository.save(subscription);
        return dtoMapper.toSubscriptionResponse(saved);
    }

    public List<Subscription> getSubscriptions(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public void removeSubscription(Long userId, Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));
        User user = subscription.getUser();
        if (!user.getId().equals(userId)) {
            throw new InvalidInputException("Subscription does not belong to this user");
        }
        user.getSubscriptions().remove(subscription);
        //subscriptionRepository.delete(subscription);
    }
    public List<TopSubscriptionDTO> findTopNSubscriptions(int n) {
        return subscriptionRepository.findTopNSubscriptions(n);
    }
}