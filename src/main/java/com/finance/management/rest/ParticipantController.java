package com.finance.management.rest;

import com.finance.management.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/participant")
public class ParticipantController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/verify-payment")
    public ResponseEntity<String> verifyPayment(@RequestParam String email, @RequestParam String otp) {
        if (paymentService.verifyPaymentOTP(email, otp)) {
            return ResponseEntity.ok("Payment verification successful.");
        } else {
            return ResponseEntity.badRequest().body("Payment verification failed.");
        }
    }

    @PostMapping("/is-payment-otp-expired")
    public ResponseEntity<String> isPaymentOTPExpired(@RequestParam String email) {
        if (paymentService.isPaymentOTPExpired(email)) {
            return ResponseEntity.ok("Payment OTP has expired.");
        } else {
            return ResponseEntity.ok("Payment OTP is still valid.");
        }
    }

    @PostMapping("/send-email-otp")
    public ResponseEntity<String> sendEmailVerification(@RequestParam String email, @RequestParam Long participantId) {
        try {
            paymentService.sendEmailVerification(email, participantId);
            return ResponseEntity.ok("Verification email has been sent.");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to send verification email.");
        }
    }
}
