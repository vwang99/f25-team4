/*
 * AppUserController
 * -----------------
 * REST controller exposing CRUD endpoints for application users.
 * Used by the frontend to create accounts, list users, and update profiles.
 * Note: Authentication is minimal for the prototype (plaintext passwords).
 */

package com.f25_team4.tether.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ImageUploadService imageUploadService;

    /**
     * GET /users
     * Returns all users in the system. Mainly used for testing.
     */
    @GetMapping
    public List<AppUser> getAllUsers() {
        System.out.println("GET /users called");
        return appUserService.getAllUsers();
    }

    /**
     * GET /users/{id}
     * Returns a single user by their id. Mainly used for testing.
     */
    @GetMapping("/{id}")
    public AppUser getUser(@PathVariable Long id) {
        System.out.println("GET /users/" + id + " called");
        return appUserService.getUserById(id).orElseThrow();
    }

    /**
     * POST /users
     * Create a new user. The frontend sends an AppUser object containing:
     * - username: the unique username for the user
     * - displayName: the display name for the user
     * - profileImageUrl: URL to the user's profile image
     * - password: plaintext password (note: minimal security for prototype)
     */
    @PostMapping
    public AppUser createUser(@RequestBody AppUser user) {
        System.out.println("POST /users called with username=" + user.getUsername());
        return appUserService.createUser(user);
    }

    /**
     * PUT /users/{id}
     * Update an existing user. The frontend sends an AppUser object containing
     * the fields to update (username, displayName, profileImageUrl, password).
     */
    @PutMapping("/{id}")
    public AppUser updateUser(@PathVariable Long id, @RequestBody AppUser user) {
        System.out.println("PUT /users/" + id + " called with username=" + user.getUsername() + " displayName=" + user.getDisplayName() + " profileImageUrl=" + user.getProfileImageUrl());
        return appUserService.updateUser(id, user);
    }

    /**
     * DELETE /users/{id}
     * Deletes a user by their id. Mainly used for testing.
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("DELETE /users/" + id + " called");
        appUserService.deleteUser(id);
    }

    /**
     * PATCH /users/{id}/subscribe
     * Toggles the subscription status of a user.
     */
    @PatchMapping("/{id}/subscribe")
    public AppUser toggleSubscription(@PathVariable Long id) {
         System.out.println("PATCH /users/" + id + "/subscribe called");
       AppUser user = appUserService.getUserById(id).orElseThrow();

       boolean currentStatus = user.isSubscribed();
       user.setSubscribed(!currentStatus);

       AppUser updatedUser = appUserService.saveUser(user);

       return updatedUser;
    }

    /**
     * POST /users/{id}/upload-avatar
     * Upload a profile picture/avatar for a user.
     * Expects a multipart/form-data request with a "file" parameter.
     * Returns the Cloudinary URL of the uploaded image.
     */
    @PostMapping("/{id}/upload-avatar")
    public ResponseEntity<ImageUploadRequest> uploadAvatar(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            System.out.println("POST /users/" + id + "/upload-avatar called");
            
            // Verify file is not empty
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(new ImageUploadRequest("File is empty"));
            }

            // Verify file is an image
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().body(new ImageUploadRequest("File must be an image"));
            }

            // Upload to Cloudinary
            String imageUrl = imageUploadService.uploadImage(file, "tether/avatars");

            // Update user's profileImageUrl
            AppUser user = appUserService.getUserById(id).orElseThrow();
            user.setProfileImageUrl(imageUrl);
            appUserService.saveUser(user);

            return ResponseEntity.ok(new ImageUploadRequest(imageUrl));
        } catch (IllegalStateException e) {
            System.err.println("Cloudinary not configured: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ImageUploadRequest("Avatar upload service not configured: " + e.getMessage()));
        } catch (IOException e) {
            System.err.println("Error uploading avatar: " + e.getMessage());
            // Check if it's an authentication error
            String errorMsg = e.getMessage();
            if (errorMsg != null && (errorMsg.contains("Invalid") || errorMsg.contains("unauthorized") || errorMsg.contains("401") || errorMsg.contains("403"))) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ImageUploadRequest("Cloudinary authentication failed. Please check your API credentials in application.properties"));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ImageUploadRequest("Upload failed: " + errorMsg));
        } catch (Exception e) {
            System.err.println("Unexpected error uploading avatar: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ImageUploadRequest("Unexpected error: " + e.getMessage()));
        }
    }

}