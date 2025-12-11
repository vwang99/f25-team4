package com.f25_team4.tether.user;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * ImageUploadService
 * ------------------
 * Handles image uploads to Cloudinary (3rd party cloud storage).
 * Supports uploading profile pictures/avatars for users.
 * 
 * Cloudinary initialization is lazy - it only initializes when first used.
 */
@Service
public class ImageUploadService {

    private Cloudinary cloudinary;
    private final String cloudName;
    private final String apiKey;
    private final String apiSecret;

    public ImageUploadService(
            @Value("${cloudinary.cloud-name:}") String cloudName,
            @Value("${cloudinary.api-key:}") String apiKey,
            @Value("${cloudinary.api-secret:}") String apiSecret) {
        this.cloudName = cloudName;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        // Cloudinary will be initialized on first use (lazy initialization)
    }

    /**
     * Initialize Cloudinary lazily on first use.
     */
    private void initializeCloudinary() throws IllegalStateException {
        if (cloudinary != null) {
            return;
        }

        if (cloudName == null || cloudName.isEmpty() || 
            apiKey == null || apiKey.isEmpty() || 
            apiSecret == null || apiSecret.isEmpty()) {
            throw new IllegalStateException(
                "Cloudinary credentials are not configured. " +
                "Please set CLOUDINARY_CLOUD_NAME, CLOUDINARY_API_KEY, and CLOUDINARY_API_SECRET environment variables.");
        }

        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }

    /**
     * Upload an image file to Cloudinary.
     * 
     * @param file the image file to upload
     * @param folder the folder in Cloudinary to store the image (e.g., "tether/avatars")
     * @return the public URL of the uploaded image
     * @throws IOException if upload fails
     * @throws IllegalStateException if Cloudinary credentials are not configured
     */
    public String uploadImage(MultipartFile file, String folder) throws IOException {
        initializeCloudinary();

        @SuppressWarnings("unchecked")
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "folder", folder,
                "resource_type", "auto"
        ));
        
        return (String) uploadResult.get("secure_url");
    }

    /**
     * Delete an image from Cloudinary by its public ID.
     * 
     * @param publicId the Cloudinary public ID of the image to delete
     * @throws IOException if deletion fails
     * @throws IllegalStateException if Cloudinary credentials are not configured
     */
    public void deleteImage(String publicId) throws IOException {
        initializeCloudinary();
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}
