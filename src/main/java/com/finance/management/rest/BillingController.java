package com.finance.management.rest;

import com.finance.management.service.PaymentService;
import com.finance.management.util.BillRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billing")
public class BillingController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/verify-bill")
    public ResponseEntity<String> verifyBillAmount(@RequestHeader("Authorization") String authToken, @RequestBody BillRequest billRequest) {
        if (paymentService.verifyBillAmount(authToken, billRequest.getExpectedBillAmount())) {
            return ResponseEntity.ok("Bill verification successful.");
        } else {
            return ResponseEntity.badRequest().body("Bill verification failed.");
        }
    }
}
