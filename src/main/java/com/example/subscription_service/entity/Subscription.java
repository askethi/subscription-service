package com.example.subscription_service.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Subscription {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)  // Используем EnumType.STRING для сохранения строки в базе
    private ServiceName serviceName;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public Subscription() {
    }

    public Subscription(User user, ServiceName serviceName, LocalDateTime startDate, LocalDateTime endDate) {
        this.user = user;
        this.serviceName = serviceName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceName getServiceName() {
        return serviceName;
    }

    public void setServiceName(ServiceName serviceName) {
        this.serviceName = serviceName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
