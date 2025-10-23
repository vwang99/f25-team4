package com.f25_team4.tether.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public List<AppUser> getAllUsers() {
        return appUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public AppUser getUser(@PathVariable Long id) {
        return appUserService.getUserById(id).orElseThrow();
    }

    @PostMapping
    public AppUser createUser(@RequestBody AppUser user) {
        return appUserService.createUser(user);
    }

    @PutMapping("/{id}")
    public AppUser updateUser(@PathVariable Long id, @RequestBody AppUser user) {
        return appUserService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
    }
}