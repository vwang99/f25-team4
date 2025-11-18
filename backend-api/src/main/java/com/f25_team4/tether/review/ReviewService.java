/*
 * ReviewService
 * -------------
 * Handles persistence and queries for Review entities used by the review endpoints.
 */

package com.f25_team4.tether.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    
    /**
     * Get all reviews in the system. Mainly used for admin purposes.
     */
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    /**
     * Get reviews specific to a chat room. The frontend calls this to populate the
     * reviews section for a room (e.g., on the room page or review summary widgets).
     */
    public List<Review> getReviewsByChatRoom(Long chatRoomId) {
        return reviewRepository.findByChatRoomId(chatRoomId);
    }

    /**
     * Persist a new review. Controllers will construct the Review entity from the
     * frontend payload (including chatRoomId and senderId references) and call this method.
     */
    public Review createReview(Review r) {
        return reviewRepository.save(r);
    }

    /**
     * Delete a review by id. Used by the controller's DELETE endpoint.
     */
    public void deleteReview(Long id) { reviewRepository.deleteById(id); }
}
