/*
 * MessageController
 * -----------------
 * Handles REST endpoints for messages. Frontend posts messages here
 * and the service persists them with sender + chatroom associations.
 */

package com.f25_team4.tether.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.f25_team4.tether.chatroom.ChatRoom;
import com.f25_team4.tether.chatroom.ChatRoomService;
import com.f25_team4.tether.user.AppUser;
import com.f25_team4.tether.user.AppUserService;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    
    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private AppUserService appUserService;

    @GetMapping
    /**
     * GET /messages
     * Returns all messages across all chat rooms. Mainly used for testing.
     */
    public List<Message> getAllMessages() {
        System.out.println("GET /messages called");
        return messageService.getAllMessages();
    }

    /**
     * GET /messages/{id}
     * Return a single message by its id. Mainly used for testing.>
     */
    @GetMapping("/{id}")
    public Message getMessage(@PathVariable Long id) {
        System.out.println("GET /messages/" + id + " called");
        return messageService.getMessageById(id).orElseThrow();
    }

    /**
     * POST /messages
     * Create a new message. The frontend sends a MessageRequest containing:
     * - senderId: the numeric id of the user sending the message
     * - chatRoomId: the numeric id of the chat room where the message is posted
     * - content: the text content of the message
     */
    @PostMapping
    public Message createMessage(@RequestBody MessageRequest request) {
        System.out.println("POST /messages called with senderId=" + request.getSenderId() + " chatRoomId=" + request.getChatRoomId() + " content=" + request.getContent());
        AppUser sender = appUserService.getUserById(request.getSenderId()).orElseThrow();
        ChatRoom chatRoom = chatRoomService.getChatRoomById(request.getChatRoomId()).orElseThrow();

        Message message = new Message(request.getContent(), sender, chatRoom);
        return messageService.createMessage(message);
    }

    /**
     * PUT /messages/{id}
     * Update a message's content. (not implemented yet)
     */
    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable Long id, @RequestBody Message message) {
        System.out.println("PUT /messages/" + id + " called with content=" + message.getContent());
        return messageService.updateMessage(id, message);
    }

    /**
     * DELETE /messages/{id}
     * Remove a message from the database. Mainly used for testing.
     */
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        System.out.println("DELETE /messages/" + id + " called");
        messageService.deleteMessage(id);
    }

    /**
     * GET /messages/{id}/messages
     * Returns all messages for a specific chat room.
     */
    @GetMapping("/{id}/messages")
    public List<Message> getMessagesForChatRoom(@PathVariable Long id) {
        System.out.println("GET /messages/" + id + "/messages called");
        ChatRoom chatRoom = chatRoomService.getChatRoomById(id).orElseThrow();
        List<Message> messages = messageService.getMessagesByChatRoom(chatRoom);
        return messages;
    }
}
