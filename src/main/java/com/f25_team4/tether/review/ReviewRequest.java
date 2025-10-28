package com.f25_team4.tether.review;

public class ReviewRequest {
    private Long userId;
    private int rating;
    private String comment;

    public ReviewRequest() {}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
