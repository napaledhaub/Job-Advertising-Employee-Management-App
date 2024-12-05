package com.finance.management.rest;

import com.finance.management.entity.Subscription;
import com.finance.management.service.SubscriptionService;
import com.finance.management.util.SubscriptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/list")
    public ResponseEntity<List<Subscription>> getSubscriptionList(@RequestHeader("Authorization") String authToken) {
        List<Subscription> subscriptionList = subscriptionService.getSubscriptionList(authToken);
        return ResponseEntity.ok(subscriptionList);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToService(@RequestHeader("Authorization") String authToken, @RequestBody SubscriptionRequest subscriptionRequest) {
        subscriptionService.subscribeToService(authToken, subscriptionRequest.getServiceMenuId());
        return ResponseEntity.ok("Subscription successful.");
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelSubscription(@RequestHeader("Authorization") String authToken, @RequestBody SubscriptionRequest subscriptionRequest) {
        subscriptionService.cancelSubscription(authToken, subscriptionRequest.getSubscriptionId());
        return ResponseEntity.ok("Subscription cancelled successfully.");
    }

    @PostMapping("/extend")
    public ResponseEntity<String> extendSubscription(@RequestHeader("Authorization") String authToken, @RequestBody SubscriptionRequest subscriptionRequest) {
        subscriptionService.extendSubscription(authToken, subscriptionRequest.getSubscriptionId(), subscriptionRequest.getNumberOfSessions());
        return ResponseEntity.ok("Session duration successfully extended.");
    }
}
