/**
 * ChatRoom entity
 * ---------------
 * Represents a discussion room. Key fields:
 * - id: PK
 * - name: the human-friendly room name used in the UI
 * - description: short description shown on index and room pages
 * - createdAt: timestamp of creation
 *
 * ChatRoom objects are created/updated by the frontend via /chatrooms endpoints.
 */
package com.f25_team4.tether.chatroom;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDateTime createdAt;
    
    private Long creatorId;

    public ChatRoom() {
        this.createdAt = LocalDateTime.now();
    }

    public ChatRoom(String name, String description) {
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }
    
    public ChatRoom(String name, String description, Long creatorId) {
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setter methods
    
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public Long getCreatorId() {
        return this.creatorId;
    }
    
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
