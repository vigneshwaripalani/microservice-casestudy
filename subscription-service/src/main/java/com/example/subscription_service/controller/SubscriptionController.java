package com.example.subscription_service.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.subscription_service.exception.BookNotAvailableException;
import com.example.subscription_service.model.Subscription;
import com.example.subscription_service.service.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public List<Subscription> getAllSubscriptions(@RequestParam(required = false) String subscriberName) {
        if (subscriberName != null) {
            return subscriptionService.getSubscriptionsBySubscriberName(subscriberName);
        }
        return subscriptionService.getAllSubscriptions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Subscription createSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.createSubscription(subscription);
    }

    @ExceptionHandler(BookNotAvailableException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleBookNotAvailableException(BookNotAvailableException ex) {
        return ex.getMessage();
    }
}