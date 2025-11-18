/*
 * ChatRoomController
 * ------------------
 * Exposes endpoints to list, create (not implemented yet), update (not implemented yet), and delete chat rooms (not implemented yet).
 * The frontend uses these endpoints to show a list of chatrooms and manage them (not implemented yet).
 */

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
        System.out.println("GET /chatrooms called");
        return chatRoomService.getAllChatRooms();
    }
    /**
     * GET /chatrooms/{id} 
     * Returns a single chat room by its id and shows the messages in the chat room.
    */
    @GetMapping("/{id}")
    public ChatRoom getChatRoom(@PathVariable Long id) {
        return chatRoomService.getChatRoomById(id).orElseThrow();
    }
    /**
     * POST /chatrooms
     * creates a new chat room with the provided details (not implemented yet)
     */
    @PostMapping
    public ChatRoom createChatRoom(@RequestBody ChatRoom room) {
        System.out.println("POST /chatrooms called with name=" + room.getName() + " description=" + room.getDescription());
        return chatRoomService.createChatRoom(room);
    }
    /**
     * PUT /chatrooms/{id}
     * updates an existing chat room with the provided details (not implemented yet)
     */
    @PutMapping("/{id}")
    public ChatRoom updateChatRoom(@PathVariable Long id, @RequestBody ChatRoom room) {
        System.out.println("PUT /chatrooms/" + id + " called with name=" + room.getName() + " description=" + room.getDescription());
        return chatRoomService.updateChatRoom(id, room);
    }
    /**
     * DELETE /chatrooms/{id}
     * deletes a chat room by its id (not implemented yet)
     */
    @DeleteMapping("/{id}")
    public void deleteChatRoom(@PathVariable Long id) {
        System.out.println("DELETE /chatrooms/" + id + " called");
        chatRoomService.deleteChatRoom(id);
    }

    /**
     * GET /chatrooms/{id}/messages
     * Returns all messages for a specific chat room.
     */
    @GetMapping("/{id}/messages")
public List<Message> getMessagesForChatRoom(@PathVariable Long id) {
    System.out.println("GET /chatrooms/" + id + "/messages called");
    ChatRoom chatRoom = chatRoomService.getChatRoomById(id).orElseThrow();
    List<Message> messages = messageService.getMessagesByChatRoom(chatRoom);
    return messages;
}



}
