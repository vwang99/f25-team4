package com.f25_team4.tether.chatroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
