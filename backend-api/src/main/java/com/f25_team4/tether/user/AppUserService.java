/*
 * AppUserService
 * --------------
 * Encapsulates business logic related to AppUser entities (create, update, find).
 * The controllers call this service for persistence operations.
 */

package com.f25_team4.tether.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepo;
    /**
     * Return all AppUser records. Used by the frontend to validate username/password
     * during the simple login flow (frontend fetches /users and searches for a match).
     */
    public List<AppUser> getAllUsers() {
        return appUserRepo.findAll();
    }

    /**
     * Find a user by id. Called by controllers that need to resolve sender ids
     * or to look up the currently logged-in user's database record.
     */
    public Optional<AppUser> getUserById(Long id) {
        return appUserRepo.findById(id);
    }

    /**
     * Create a new AppUser. The controller exposes POST /users which accepts
     * an AppUser JSON payload (username, email, password, phoneNumber).
     */
    public AppUser createUser(AppUser user) {
        return appUserRepo.save(user);
    }

    /**
     * Update an existing user. Only the provided fields are copied to the
     * persisted entity. The frontend calls PUT /users/{id} to sync profile
     * fields (displayName, bio) while keeping the avatar as a local data URL.
     */
    public AppUser updateUser(Long id, AppUser updatedUser) {
        AppUser user = appUserRepo.findById(id).orElseThrow();
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setDisplayName(updatedUser.getDisplayName());
        user.setBio(updatedUser.getBio());
        user.setProfileImageUrl(updatedUser.getProfileImageUrl());
        return appUserRepo.save(user);
    }

    /**
     * Delete user by id. Used by the UI's "delete profile" action which removes
     * local profile data and clears session; backend deletion is available here
     * but the frontend primarily manages local profile state for avatars.
     */
    public void deleteUser(Long id) {
        appUserRepo.deleteById(id);
    }

    /**
     * Save or persist a user entity directly. Helper used by controllers that
     * toggle subscription status or apply partial updates.
     */
    public AppUser saveUser(AppUser user) {
        AppUser savedUser = appUserRepo.save(user);
        return savedUser;
    }

}