package com.example.subscription_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.subscription_service.exception.BookNotAvailableException;
import com.example.subscription_service.model.Book;
import com.example.subscription_service.model.Subscription;
import com.example.subscription_service.repository.SubscriptionRepository;

import jakarta.transaction.Transactional;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public List<Subscription> getSubscriptionsBySubscriberName(String subscriberName) {
        return subscriptionRepository.findBySubscriberName(subscriberName);
    }

    @Transactional
    public Subscription createSubscription(Subscription subscription) {
        // Check if the book is available
        String bookServiceUrl = "http://localhost:8080/books/" + subscription.getBookId();
        Book book = restTemplate.getForObject(bookServiceUrl, Book.class);

        if (book != null && book.getCopiesAvailable() > 0) {
            // Update the book's available copies
            book.setCopiesAvailable(book.getCopiesAvailable() - 1);
            restTemplate.put(bookServiceUrl, book);

            // Save the subscription
            return subscriptionRepository.save(subscription);
        } else {
            throw new BookNotAvailableException("Book copies not available for subscription.");
        }
    }
}