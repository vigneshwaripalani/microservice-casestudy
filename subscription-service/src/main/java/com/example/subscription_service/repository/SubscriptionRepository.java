package com.example.subscription_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.subscription_service.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findBySubscriberName(String subscriberName);
}