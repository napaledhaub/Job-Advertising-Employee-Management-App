package com.finance.management.rest;

import com.finance.management.entity.Subscription;
import com.finance.management.service.SubscriptionService;
import com.finance.management.util.Response;
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
    public ResponseEntity<?> getSubscriptionList(@RequestHeader("Authorization") String authToken) {
        Response response = new Response();
        try {
            List<Subscription> subscriptionList = subscriptionService.getSubscriptionList(authToken);
            return ResponseEntity.ok(subscriptionList);
        } catch (Exception e) {
            response.setMessage("Get subscription list failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/subscribe")
    public ResponseEntity<?> subscribeToService(@RequestHeader("Authorization") String authToken, @RequestParam Long serviceId) {
        Response response = new Response();
        try {
            subscriptionService.subscribeToService(authToken, serviceId);
            response.setMessage("Subscription successful");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Subscribe to this service failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/cancel")
    public ResponseEntity<?> cancelSubscription(@RequestHeader("Authorization") String authToken, @RequestParam Long subscriptionId) {
        Response response = new Response();
        try {
            subscriptionService.cancelSubscription(authToken, subscriptionId);
            response.setMessage("Subscription cancelled successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Subscribe to this service failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/extend")
    public ResponseEntity<?> extendSubscription(@RequestHeader("Authorization") String authToken, @RequestParam Long subscriptionId) {
        Response response = new Response();
        try {
            subscriptionService.extendSubscription(authToken, subscriptionId);
            response.setMessage("Session duration successfully extended");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Session extension failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }
}
