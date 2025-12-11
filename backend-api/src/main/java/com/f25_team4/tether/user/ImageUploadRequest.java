package com.f25_team4.tether.user;

/**
 * ImageUploadRequest
 * ------------------
 * Response returned after a successful image upload.
 * Contains the URL where the image can be accessed.
 */
public class ImageUploadRequest {
    private String imageUrl;

    public ImageUploadRequest() {
    }

    public ImageUploadRequest(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
