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
    
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByChatRoom(Long chatRoomId) {
        return reviewRepository.findByChatRoomId(chatRoomId);
    }

    public Review createReview(Review r) {
        return reviewRepository.save(r);
    }

    public void deleteReview(Long id) { reviewRepository.deleteById(id); }
}
