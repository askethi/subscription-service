package com.example.subscription_service.repository;

import com.example.subscription_service.dto.TopSubscriptionDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepositoryCustom {

    private final EntityManager entityManager;

    public SubscriptionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TopSubscriptionDTO> findTopNSubscriptions(int n) {
        String queryStr = "SELECT new com.example.subscription_service.dto.TopSubscriptionDTO(s.serviceName, COUNT(s)) " +
                "FROM Subscription s " +
                "GROUP BY s.serviceName " +
                "ORDER BY COUNT(s) DESC";

        TypedQuery<TopSubscriptionDTO> query = entityManager.createQuery(queryStr, TopSubscriptionDTO.class);
        query.setMaxResults(n);

        return query.getResultList();
    }
}