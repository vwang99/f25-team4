package com.f25_team4.tether.user;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean subscribed = false;

    private LocalDateTime createdAt;

    public AppUser() {
        this.createdAt = LocalDateTime.now();
    }

    public AppUser(String username, String email, String password, boolean subscribed, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.subscribed = subscribed;
        this.phoneNumber = phoneNumber;
        this.createdAt = LocalDateTime.now();
    }


// ID (read-only)
public Long getId() {
    return this.id;
}

// Username
public String getUsername() {
    return this.username;
}

public void setUsername(String username) {
    this.username = username;
}

// Email
public String getEmail() {
    return this.email;
}

public void setEmail(String email) {
    this.email = email;
}

// Password
public String getPassword() {
    return this.password;
}

public void setPassword(String password) {
    this.password = password;
}

// Created At
public LocalDateTime getCreatedAt() {
    return this.createdAt;
}

public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
}

// Subscribed
public boolean isSubscribed() {
    return this.subscribed;
}

public void setSubscribed(boolean subscribed) {
    this.subscribed = subscribed;
}

// Phone Number
public String getPhoneNumber() {
    return this.phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
}

}