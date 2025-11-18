/*
 * ChatRoomService
 * ---------------
 * Service layer for chat room operations. Keeps persistence logic out of controllers.
 */

package com.f25_team4.tether.chatroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService {
    
    @Autowired
    private ChatRoomRepository chatRoomRepo;

    /**
     * Return all chat rooms. Used by the index or manage pages to show a list of chatrooms.
     * The frontend will request this on load.
     */
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepo.findAll();
    }

    /**
     * Find a chat room by id. Controllers use this when loading a single room for edit or display.
     * Returns an Optional that will be empty if the id is not present.
     */
    public Optional<ChatRoom> getChatRoomById(Long id) {
        return chatRoomRepo.findById(id);
    }

    /**
     * Create and persist a new ChatRoom. Expects the controller to populate the ChatRoom object
     * from frontend form data before calling this method.
     */
    public ChatRoom createChatRoom(ChatRoom room) {
        return chatRoomRepo.save(room);
    }

    /**
     * Update an existing chat room's fields (name, description). The service loads the
     * persisted entity, applies the updated fields, and saves it so JPA handles merging.
     */
    public ChatRoom updateChatRoom(Long id, ChatRoom updatedRoom) {
        ChatRoom room = chatRoomRepo.findById(id).orElseThrow();

        room.setName(updatedRoom.getName());
        room.setDescription(updatedRoom.getDescription());

        return chatRoomRepo.save(room);
    }

    /**
     * Delete a chat room by id. Controllers call this for delete operations.
     */
    public void deleteChatRoom(Long id) {
        chatRoomRepo.deleteById(id);
    }
}
