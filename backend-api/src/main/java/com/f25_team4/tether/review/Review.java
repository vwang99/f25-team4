/**
 * Review entity
 * -------------
 * Lightweight review attached to a chat room. Stored fields:
 * - id: PK
 * - rating: integer 1-5
 * - content: optional text comment
 * - senderId: numeric id of the AppUser who wrote the review
 * - chatRoomId: numeric id of the ChatRoom being reviewed
 * - createdAt: timestamp
 *
 * Reviews are intentionally simple: the relationship is stored as ids rather
 * than object references to keep the model lightweight for this prototype.
 */
package com.f25_team4.tether.review;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.f25_team4.tether.user.AppUser;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;

    @Column(length = 2000)
    private String content;
    private Long senderId;
    private Long chatRoomId;
    private LocalDateTime createdAt;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AppUser sender;

    public Review() {
        this.createdAt = LocalDateTime.now();
    }

    public Review(int rating, String content, Long senderId, Long chatRoomId) {
        this.rating = rating;
        this.content = content;
        this.senderId = senderId;
        this.chatRoomId = chatRoomId;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setter methods
    public Long getId() { 
        return id; 
    }

    public int getRating() { 
        return rating; 
    }

    public void setRating(int rating) { 
        this.rating = rating; 
    }

    public String getContent() { 
        return content; 
    }

    public void setContent(String content) { 
        this.content = content; 
    }

    public Long getSenderId() { 
        return senderId; 
    }

    public void setSenderId(Long senderId) { 
        this.senderId = senderId; 
    }

    public Long getChatRoomId() { 
        return chatRoomId; 
    }

    public void setChatRoomId(Long chatRoomId) { 
        this.chatRoomId = chatRoomId; 
    }

    public LocalDateTime getCreatedAt() { 
        return createdAt; 
    }

    public void setCreatedAt(LocalDateTime createdAt) { 
        this.createdAt = createdAt; 
    }

    public AppUser getSender() { 
        return sender; 
    }

    public void setSender(AppUser sender) { 
        this.sender = sender; 
    }
    
}
