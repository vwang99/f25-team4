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

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepo.findAll();
    }

    public Optional<ChatRoom> getChatRoomById(Long id) {
        return chatRoomRepo.findById(id);
    }
    
    public List<ChatRoom> getChatRoomsByCreator(Long creatorId) {
        return chatRoomRepo.findAll().stream()
            .filter(room -> creatorId.equals(room.getCreatorId()))
            .collect(Collectors.toList());
    }
    
    public int getChatRoomCountByCreator(Long creatorId) {
        return getChatRoomsByCreator(creatorId).size();
    }
    
    public boolean canCreateChatRoom(Long creatorId, boolean isSubscribed) {
        int currentCount = getChatRoomCountByCreator(creatorId);
        int maxRooms = isSubscribed ? 6 : 3;
        return currentCount < maxRooms;
    }
    
    public int getMaxChatRoomsForUser(boolean isSubscribed) {
        return isSubscribed ? 6 : 3;
    }

    public ChatRoom createChatRoom(ChatRoom room) {
        return chatRoomRepo.save(room);
    }

    public ChatRoom updateChatRoom(Long id, ChatRoom updatedRoom) {
        ChatRoom room = chatRoomRepo.findById(id).orElseThrow();

        room.setName(updatedRoom.getName());
        room.setDescription(updatedRoom.getDescription());

        return chatRoomRepo.save(room);
    }

    public void deleteChatRoom(Long id) {
        chatRoomRepo.deleteById(id);
    }
}
