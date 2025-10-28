package com.f25_team4.tether.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/chatrooms/{id}/reviews")
    public Review leaveReview(@PathVariable Long id, @RequestBody ReviewRequest req) {
        return reviewService.leaveReview(id, req.getUserId(), req.getRating(), req.getComment());
    }

    @GetMapping("/chatrooms/{id}/reviews")
    public List<Review> getReviews(@PathVariable Long id) {
        return reviewService.getReviewsByChatRoom(id);
    }
}
