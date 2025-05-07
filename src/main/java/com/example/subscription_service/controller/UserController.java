package com.example.subscription_service.controller;

import com.example.subscription_service.entity.ServiceName;
import com.example.subscription_service.entity.Subscription;
import com.example.subscription_service.entity.User;
import com.example.subscription_service.service.SubscriptionService;
import com.example.subscription_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public UserController(UserService userService, SubscriptionService subscriptionService) {
        this.userService = userService;
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
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
    public Subscription addSubscription(@PathVariable Long id, @RequestParam ServiceName serviceName) {
        Subscription subscription = new Subscription();
        subscription.setId(id);
        subscription.setServiceName(serviceName);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(LocalDateTime.now().plusMonths(1));
        return subscriptionService.addSubscription(id, subscription);
    }

    @GetMapping("/{id}/subscriptions")
    public List<Subscription> getSubscriptions(@PathVariable Long id) {
        return subscriptionService.getSubscriptions(id);
    }

    @DeleteMapping("/{id}/subscriptions/{subId}")
    public void removeSubscription(@PathVariable Long id, @PathVariable Long subId) {
        subscriptionService.removeSubscription(id, subId);
    }
}