/*
 * ReviewController
 * ----------------
 * Simple REST controller to create, list, and delete reviews tied to chat rooms.
 */

package com.f25_team4.tether.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.f25_team4.tether.chatroom.ChatRoomService;
import com.f25_team4.tether.user.AppUserService;
import com.f25_team4.tether.chatroom.ChatRoom;
import com.f25_team4.tether.user.AppUser;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ChatRoomService chatRoomService;
    /**
     * GET /reviews
     * Returns all reviews, or reviews for a specific chat room if chatRoomId is provided.
     */
    @GetMapping
    public List<Review> getReviews(@RequestParam(required = false) Long chatRoomId) {
        System.out.println("GET /reviews called chatRoomId=" + chatRoomId);
        List<Review> reviews;
        if (chatRoomId == null) {
            reviews = reviewService.getAllReviews();
        } else {
            reviews = reviewService.getReviewsByChatRoom(chatRoomId);
        }
        // Corellate each review with sender information
        for (Review r : reviews) {
            if (r.getSenderId() != null) {
                appUserService.getUserById(r.getSenderId()).ifPresent(r::setSender);
            }
        }
        return reviews;
    }
    /**
     * POST /reviews
     * Create a new review. The frontend sends a ReviewRequest containing:
     * - senderId: the numeric id of the user sending the review
     * - chatRoomId: the numeric id of the chat room being reviewed
     * - rating: the numeric rating (e.g., 1-5)
     * - content: the text content of the review
     */
    @PostMapping
    public Review createReview(@RequestBody ReviewRequest req) {
        System.out.println("POST /reviews called senderId=" + req.getSenderId() + " chatRoomId=" + req.getChatRoomId() + " rating=" + req.getRating());
        // validate existence of sender and chatroom
        AppUser sender = appUserService.getUserById(req.getSenderId()).orElseThrow();
        ChatRoom room = chatRoomService.getChatRoomById(req.getChatRoomId()).orElseThrow();
        Review r = new Review(req.getRating(), req.getContent(), sender.getId(), room.getId());
        Review created = reviewService.createReview(r);
        // Corellates response with sender information
        created.setSender(sender);
        return created;
    }
    /**
     * DELETE /reviews/{id}
     * Deletes a review by its id. Mainly used for testing.
     */
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        System.out.println("DELETE /reviews/" + id + " called");
        reviewService.deleteReview(id);
    }
}
