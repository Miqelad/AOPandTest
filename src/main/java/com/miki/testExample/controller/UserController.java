package com.miki.testExample.controller;

import com.miki.testExample.entity.User;
import com.miki.testExample.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserManager userManager;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userManager.getAll();


    }

    @GetMapping("/{id}")
    public User getOneUserById(@PathVariable Integer id) {
        return userManager.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Integer id) {
        User byId = userManager.findById(id);
        userManager.delete(id);
        return "Delete user: " + byId.getName() + " " + byId.getLastname();
    }

    @PostMapping("/")
    public String addNewUser(@RequestBody User user) {
        userManager.save(user);
        return "New user: " + user.getName() + " " + user.getLastname();

    }
}
