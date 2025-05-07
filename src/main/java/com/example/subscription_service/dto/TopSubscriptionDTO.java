package com.example.subscription_service.dto;

import com.example.subscription_service.entity.ServiceName;

public class TopSubscriptionDTO {
    private ServiceName serviceName;
    private Long popularity;

    public TopSubscriptionDTO(ServiceName serviceName, Long popularity) {
        this.serviceName = serviceName;
        this.popularity = popularity;
    }

    public ServiceName getServiceName() {
        return serviceName;
    }

    public void setServiceName(ServiceName serviceName) {
        this.serviceName = serviceName;
    }

    public Long getPopularity() {
        return popularity;
    }

    public void setPopularity(Long popularity) {
        this.popularity = popularity;
    }
}