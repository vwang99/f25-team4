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
import com.f25_team4.tether.user.AppUser;
import com.f25_team4.tether.user.AppUserService;

@RestController
@RequestMapping("/chatrooms")
public class ChatRoomController {
    
    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MessageService messageService;
    
    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public List<ChatRoom> getAllChatRooms() {
        System.out.println("GET /chatrooms called");
        return chatRoomService.getAllChatRooms();
    }
    
    /**
     * GET /chatrooms/user/{userId}
     * Returns only the chat rooms created by this user.
     */
    @GetMapping("/user/{userId}")
    public List<ChatRoom> getUserChatRooms(@PathVariable Long userId) {
        System.out.println("GET /chatrooms/user/" + userId + " called");
        return chatRoomService.getChatRoomsByCreator(userId);
    }
    
    /**
     * GET /chatrooms/user/{userId}/count
     * Returns the count of chatrooms and max allowed for the user.
     */
    @GetMapping("/user/{userId}/count")
    public java.util.Map<String, Object> getUserChatRoomCount(@PathVariable Long userId) {
        System.out.println("GET /chatrooms/user/" + userId + "/count called");
        AppUser user = appUserService.getUserById(userId).orElseThrow();
        int count = chatRoomService.getChatRoomCountByCreator(userId);
        int maxRooms = chatRoomService.getMaxChatRoomsForUser(user.isSubscribed());
        
        java.util.Map<String, Object> response = new java.util.HashMap<>();
        response.put("count", count);
        response.put("max", maxRooms);
        response.put("canCreate", count < maxRooms);
        return response;
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
     * creates a new chat room with the provided details.
     * Expects the request body to include creatorId and checks subscription limits.
     */
    @PostMapping
    public ChatRoom createChatRoom(@RequestBody ChatRoom room) {
        System.out.println("POST /chatrooms called with name=" + room.getName() + " description=" + room.getDescription() + " creatorId=" + room.getCreatorId());
        
        if (room.getCreatorId() == null) {
            throw new RuntimeException("creatorId is required");
        }
        
        // Get the user and check subscription status
        AppUser creator = appUserService.getUserById(room.getCreatorId()).orElseThrow();
        
        // Check if user can create more chatrooms
        if (!chatRoomService.canCreateChatRoom(room.getCreatorId(), creator.isSubscribed())) {
            int max = chatRoomService.getMaxChatRoomsForUser(creator.isSubscribed());
            throw new RuntimeException("You have reached the maximum number of chatrooms (" + max + "). Subscribe to create more.");
        }
        
        return chatRoomService.createChatRoom(room);
    }
    /**
     * PUT /chatrooms/{id}
     * updates an existing chat room with the provided details.
     * Only the creator can update their room.
     */
    @PutMapping("/{id}")
    public ChatRoom updateChatRoom(@PathVariable Long id, @RequestBody ChatRoom room) {
        System.out.println("PUT /chatrooms/" + id + " called with name=" + room.getName() + " description=" + room.getDescription());
        
        ChatRoom existing = chatRoomService.getChatRoomById(id).orElseThrow();
        
        // Check if the requester is the creator
        if (room.getCreatorId() != null && !existing.getCreatorId().equals(room.getCreatorId())) {
            throw new IllegalArgumentException("You can only update chatrooms you created");
        }
        
        return chatRoomService.updateChatRoom(id, room);
    }
    /**
     * DELETE /chatrooms/{id}
     * deletes a chat room by its id.
     * Only the creator can delete their room.
     */
    @DeleteMapping("/{id}")
    public void deleteChatRoom(@PathVariable Long id, @RequestParam(required = false) Long creatorId) {
        System.out.println("DELETE /chatrooms/" + id + " called");
        
        ChatRoom existing = chatRoomService.getChatRoomById(id).orElseThrow();
        
        // Check if the requester is the creator (if creatorId is provided)
        if (creatorId != null && !existing.getCreatorId().equals(creatorId)) {
            throw new IllegalArgumentException("You can only delete chatrooms you created");
        }
        
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
