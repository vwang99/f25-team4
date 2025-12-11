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

    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepo.findById(id);
    }

    public Message createMessage(Message message) {
        return messageRepo.save(message);
    }

    public Message updateMessage(Long id, Message updatedMessage) {
        Message message = messageRepo.findById(id).orElseThrow();

        message.setContent(updatedMessage.getContent());
        message.setTimestamp(updatedMessage.getTimestamp());
        message.setSender(updatedMessage.getSender());
        message.setChatRoom(updatedMessage.getChatRoom());

        return messageRepo.save(message);
    }

    public void deleteMessage(Long id) {
        messageRepo.deleteById(id);
    }

public List<Message> getMessagesByChatRoom(ChatRoom chatRoom) {
    List<Message> messages = messageRepo.findByChatRoom(chatRoom);
    return messages;
}

}
