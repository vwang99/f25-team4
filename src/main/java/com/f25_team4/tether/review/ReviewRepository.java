package com.f25_team4.tether.review;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.f25_team4.tether.chatroom.ChatRoom;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByChatRoom(ChatRoom chatRoom);
}
