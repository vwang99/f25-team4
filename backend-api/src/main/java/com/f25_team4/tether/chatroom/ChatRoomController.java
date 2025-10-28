package com.f25_team4.tether.chatroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.f25_team4.tether.message.Message;
import com.f25_team4.tether.message.MessageService;

@RestController
@RequestMapping("/chatrooms")
public class ChatRoomController {
    
    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @GetMapping("/{id}")
    public ChatRoom getChatRoom(@PathVariable Long id) {
        return chatRoomService.getChatRoomById(id).orElseThrow();
    }

    @PostMapping
    public ChatRoom createChatRoom(@RequestBody ChatRoom room) {
        return chatRoomService.createChatRoom(room);
    }

    @PutMapping("/{id}")
    public ChatRoom updateChatRoom(@PathVariable Long id, @RequestBody ChatRoom room) {
        return chatRoomService.updateChatRoom(id, room);
    }

    @DeleteMapping("/{id}")
    public void deleteChatRoom(@PathVariable Long id) {
        chatRoomService.deleteChatRoom(id);
    }

    @GetMapping("/{id}/messages")
public List<Message> getMessagesForChatRoom(@PathVariable Long id) {
    ChatRoom chatRoom = chatRoomService.getChatRoomById(id).orElseThrow();
    List<Message> messages = messageService.getMessagesByChatRoom(chatRoom);
    return messages;
}



}
