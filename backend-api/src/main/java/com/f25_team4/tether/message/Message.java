/**
 * Message entity
 * ----------------
 * Represents a single chat message stored in the database. Important fields:
 * - id: primary key
 * - content: the message text
 * - timestamp: when the message was created
 * - sender: Many-to-one relation to AppUser (the user who sent the message)
 * - chatRoom: Many-to-one relation to ChatRoom (the room the message belongs to)
 *
 * The frontend constructs message requests with senderId and chatRoomId and
 * the controllers/services resolve these associations before persisting.
 */
package com.f25_team4.tether.message;
import com.f25_team4.tether.user.AppUser;
import com.f25_team4.tether.chatroom.ChatRoom;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String content;
private LocalDateTime timestamp;

@ManyToOne
private AppUser sender;

@ManyToOne
private ChatRoom chatRoom;

public Message() {
    this.timestamp = LocalDateTime.now();
}

public Message(String content, AppUser sender, ChatRoom chatRoom) {
    this.content = content;
    this.sender = sender;
    this.chatRoom = chatRoom;
    this.timestamp = LocalDateTime.now();
}

// Getters and setter methods
public Long getId() {
    return this.id;
}

public String getContent() {
    return this.content;
}

public void setContent(String content) {
    this.content = content;
}

public LocalDateTime getTimestamp() {
    return this.timestamp;
}

public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
}

public AppUser getSender() {
    return this.sender;
}

public void setSender(AppUser sender) {
    this.sender = sender;
}

public ChatRoom getChatRoom() {
    return this.chatRoom;
}

public void setChatRoom(ChatRoom chatRoom) {
    this.chatRoom = chatRoom;
}

}






