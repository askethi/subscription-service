package com.example.subscription_service.dto;

import com.example.subscription_service.entity.ServiceName;
import com.example.subscription_service.entity.Subscription;
import com.example.subscription_service.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoMapper {

    public SubscriptionDTO toSubscriptionDto(Subscription subscription) {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setId(subscription.getId());
        dto.setServiceName(ServiceName.valueOf(subscription.getServiceName().name()));
        dto.setStartDate(subscription.getStartDate());
        dto.setEndDate(subscription.getEndDate());
        return dto;
    }

    public UserDTO toUserDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        List<SubscriptionDTO> subscriptions = user.getSubscriptions()
                .stream()
                .map(this::toSubscriptionDto)
                .collect(Collectors.toList());
        dto.setSubscriptions(subscriptions);
        return dto;
    }

    public SubscriptionResponse toSubscriptionResponse(Subscription subscription) {
        SubscriptionResponse response = new SubscriptionResponse();
        response.setId(subscription.getId());
        response.setServiceName(subscription.getServiceName().name());
        response.setStartDate(subscription.getStartDate());
        response.setEndDate(subscription.getEndDate());
        response.setUserId(subscription.getUser().getId());
        return response;
    }
}