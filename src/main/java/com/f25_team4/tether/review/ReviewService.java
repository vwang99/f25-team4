package com.f25_team4.tether.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.f25_team4.tether.chatroom.ChatRoomRepository;
import com.f25_team4.tether.chatroom.ChatRoom;
import com.f25_team4.tether.user.AppUserRepository;
import com.f25_team4.tether.user.AppUser;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private ChatRoomRepository chatRoomRepo;

    @Autowired
    private AppUserRepository appUserRepo;

    public Review leaveReview(Long chatRoomId, Long userId, int rating, String comment) {
        ChatRoom room = chatRoomRepo.findById(chatRoomId).orElseThrow();
        AppUser user = appUserRepo.findById(userId).orElseThrow();

        // sanitize rating
        int r = Math.max(1, Math.min(5, rating));

        Review review = new Review(room, user, r, comment);
        return reviewRepo.save(review);
    }

    public List<Review> getReviewsByChatRoom(Long chatRoomId) {
        ChatRoom room = chatRoomRepo.findById(chatRoomId).orElseThrow();
        return reviewRepo.findByChatRoom(room);
    }
}
