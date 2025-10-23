package com.f25_team4.tether.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepo;

    public List<AppUser> getAllUsers() {
        return appUserRepo.findAll();
    }

    public Optional<AppUser> getUserById(Long id) {
        return appUserRepo.findById(id);
    }

    public AppUser createUser(AppUser user) {
        return appUserRepo.save(user);
    }

    public AppUser updateUser(Long id, AppUser updatedUser) {
        AppUser user = appUserRepo.findById(id).orElseThrow();
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        return appUserRepo.save(user);
    }

    public void deleteUser(Long id) {
        appUserRepo.deleteById(id);
    }
}