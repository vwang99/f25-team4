package com.f25_team4.tether.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.f25_team4.tether.chatroom.ChatRoom;
import com.f25_team4.tether.chatroom.ChatRoomService;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    
    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Message getMessage(@PathVariable Long id) {
        return messageService.getMessageById(id).orElseThrow();
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        return messageService.createMessage(message);
    }

    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable Long id, @RequestBody Message message) {
        return messageService.updateMessage(id, message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }

    @GetMapping("/{id}/messages")
    public List<Message> getMessagesForChatRoom(@PathVariable Long id) {
        ChatRoom chatRoom = chatRoomService.getChatRoomById(id).orElseThrow();
        List<Message> messages = messageService.getMessagesByChatRoom(chatRoom);
        return messages;
}
}
