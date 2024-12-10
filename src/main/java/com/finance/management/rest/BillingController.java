package com.finance.management.rest;

import com.finance.management.service.PaymentService;
import com.finance.management.util.BillRequest;
import com.finance.management.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billing")
public class BillingController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/verify-bill")
    public ResponseEntity<?> verifyBillAmount(@RequestHeader("Authorization") String authToken, @RequestBody BillRequest billRequest) {
        Response response = new Response();
        try {
            if (paymentService.verifyBillAmount(authToken, billRequest.getExpectedBillAmount())) {
                response.setMessage("Bill verification successful");
                return ResponseEntity.ok(response);
            }
            response.setMessage("Bill verification failed");
        } catch (Exception e) {
            response.setMessage("Bill verification failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }
}
