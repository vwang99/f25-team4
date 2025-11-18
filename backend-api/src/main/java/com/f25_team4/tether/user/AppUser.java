/**
 * AppUser entity
 * -------------
 * Represents a user.
 * - id: primary key
 * - username/password: credentials for the simple prototype auth
 * - email/phoneNumber: contact info
 * - subscribed: feature flag used by UI (mock subscription)
 * - displayName/bio/profileImageUrl: profile information shown in UI
 * - createdAt: when the account was created
 *
 * Security is minimal for now
 */
package com.f25_team4.tether.user;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    private String email;

    @Column(nullable = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean subscribed = false;

    private LocalDateTime createdAt;

    @Column(nullable = true)
    private String displayName;
    
    @Column(nullable = true)
    private String bio;

    @Column(nullable = true)
    private String profileImageUrl;

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

    // Getters and setter methods


public Long getId() {
    return this.id;
}

public String getUsername() {
    return this.username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getEmail() {
    return this.email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPassword() {
    return this.password;
}

public void setPassword(String password) {
    this.password = password;
}

public LocalDateTime getCreatedAt() {
    return this.createdAt;
}

public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
}

public boolean isSubscribed() {
    return this.subscribed;
}

public void setSubscribed(boolean subscribed) {
    this.subscribed = subscribed;
}

public String getPhoneNumber() {
    return this.phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
}

public String getDisplayName() {
    return this.displayName;
}

public void setDisplayName(String displayName) {
    this.displayName = displayName;
}

public String getBio() {
    return this.bio;
}

public void setBio(String bio) {
    this.bio = bio;
}

public String getProfileImageUrl() {
    return this.profileImageUrl;
}

public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
}
}