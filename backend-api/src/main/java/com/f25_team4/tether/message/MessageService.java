/*
 * MessageService
 * --------------
 * Service responsible for persisting and querying messages. Used by MessageController.
 */

package com.f25_team4.tether.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f25_team4.tether.chatroom.ChatRoom;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepo;

    /**
     * Retrieve all messages in the system.
     * Used by the frontend when displaying global message lists or for debugging.
     * Returns messages in arbitrary order; controllers may sort or filter as needed.
     */
    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }

    /**
     * Find a single message by its ID.
     * Controllers call this when they need the full message object for editing or display.
     * Returns an Optional which will be empty if the id does not exist.
     */
    public Optional<Message> getMessageById(Long id) {
        return messageRepo.findById(id);
    }

    /**
     * Creates a new message to the database.
     * The controller performing the POST translates the frontend payload into a Message entity
     * and calls this method to save it.
     */
    public Message createMessage(Message message) {
        return messageRepo.save(message);
    }

    /**
     * Update an existing message. The service loads the existing entity, applies changes,
     * and saves it. This keeps the controller simple and ensures JPA manages merging.
     */
    public Message updateMessage(Long id, Message updatedMessage) {
        Message message = messageRepo.findById(id).orElseThrow();

        message.setContent(updatedMessage.getContent());
        message.setTimestamp(updatedMessage.getTimestamp());
        message.setSender(updatedMessage.getSender());
        message.setChatRoom(updatedMessage.getChatRoom());

        return messageRepo.save(message);
    }

    /**
     * Delete a message by id. Controllers call this for delete endpoints.
     */
    public void deleteMessage(Long id) {
        messageRepo.deleteById(id);
    }

    /**
     * Return all messages that belong to a specific chat room. The controller will call
     * this to load messages for a room view. The repository method performs the query.
     */
public List<Message> getMessagesByChatRoom(ChatRoom chatRoom) {
    List<Message> messages = messageRepo.findByChatRoom(chatRoom);
    return messages;
}

}
