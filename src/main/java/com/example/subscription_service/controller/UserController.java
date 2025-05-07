package com.example.subscription_service.controller;

import com.example.subscription_service.dto.*;
import com.example.subscription_service.entity.User;
import com.example.subscription_service.service.SubscriptionService;
import com.example.subscription_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private final DtoMapper dtoMapper;

    @Autowired
    public UserController(UserService userService, SubscriptionService subscriptionService, DtoMapper dtoMapper) {
        this.userService = userService;
        this.subscriptionService = subscriptionService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return userService.getUser(id)
                .map(dtoMapper::toUserDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<SubscriptionResponse> addSubscription(@PathVariable Long id, @RequestBody SubscriptionRequest request) {
        return ResponseEntity.ok(subscriptionService.addSubscription(id, request));
    }

    @GetMapping("/{id}/subscriptions")
    public List<SubscriptionDTO> getSubscriptions(@PathVariable Long id) {
        return subscriptionService.getSubscriptions(id)
                .stream()
                .map(dtoMapper::toSubscriptionDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}/subscriptions/{subId}")
    public void removeSubscription(@PathVariable Long id, @PathVariable Long subId) {
        subscriptionService.removeSubscription(id, subId);
    }
}