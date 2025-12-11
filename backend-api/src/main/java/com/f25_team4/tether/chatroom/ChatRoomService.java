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
import java.util.stream.Collectors;

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
     * Get all chat rooms created by a specific user.
     */
    public List<ChatRoom> getChatRoomsByCreator(Long creatorId) {
        return chatRoomRepo.findAll().stream()
            .filter(room -> creatorId.equals(room.getCreatorId()))
            .collect(Collectors.toList());
    }
    
    /**
     * Get the count of chat rooms created by a user.
     */
    public int getChatRoomCountByCreator(Long creatorId) {
        return getChatRoomsByCreator(creatorId).size();
    }
    
    /**
     * Check if a user can create another chat room based on subscription status.
     * Non-subscribed users can have max 3 rooms.
     * Subscribed users can have max 6 rooms.
     */
    public boolean canCreateChatRoom(Long creatorId, boolean isSubscribed) {
        int currentCount = getChatRoomCountByCreator(creatorId);
        int maxRooms = isSubscribed ? 6 : 3;
        return currentCount < maxRooms;
    }
    
    /**
     * Get the max chatrooms allowed for a user.
     */
    public int getMaxChatRoomsForUser(boolean isSubscribed) {
        return isSubscribed ? 6 : 3;
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
