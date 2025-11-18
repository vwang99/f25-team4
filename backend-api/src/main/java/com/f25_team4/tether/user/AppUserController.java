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

import java.util.List;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

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

}