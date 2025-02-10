package com.example.subscription_service.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Subscription {
    @Id
    @Column(name="SUBSCRIBER_NAME ")
    private String subscriberName;
    @Column(name="DATE_SUBSCRIBED")
    private LocalDate dateSubscribed;
    @Column(name="DATE_RETURNED")
    private LocalDate dateReturned;
    @Column(name="BOOK_ID")
    private String bookId;
    
	public String getSubscriberName() {
		return subscriberName;
	}
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}
	public LocalDate getDateSubscribed() {
		return dateSubscribed;
	}
	public void setDateSubscribed(LocalDate dateSubscribed) {
		this.dateSubscribed = dateSubscribed;
	}
	public LocalDate getDateReturned() {
		return dateReturned;
	}
	public void setDateReturned(LocalDate dateReturned) {
		this.dateReturned = dateReturned;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

    
}
