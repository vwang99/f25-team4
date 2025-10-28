package com.f25_team4.tether.message;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.f25_team4.tether.chatroom.ChatRoom;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByChatRoom(ChatRoom chatRoom);
}
