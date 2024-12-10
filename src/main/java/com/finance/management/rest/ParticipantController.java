package com.finance.management.rest;

import com.finance.management.service.PaymentService;
import com.finance.management.util.EmailRequest;
import com.finance.management.util.Response;
import com.finance.management.util.VerificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participant")
public class ParticipantController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/verify-payment")
    public ResponseEntity<?> verifyPayment(@RequestBody VerificationRequest verificationRequest) {
        Response response = new Response();
        try {
            if (paymentService.verifyPaymentOTP(verificationRequest.getEmail(), verificationRequest.getOtp())) {
                response.setMessage("Payment verification successful");
                return ResponseEntity.ok(response);
            }
            response.setMessage("Payment verification failed");
        } catch (Exception e) {
            response.setMessage("Payment verification failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/is-payment-otp-expired")
    public ResponseEntity<?> isPaymentOTPExpired(@RequestBody EmailRequest emailRequest) {
        Response response = new Response();
        try {
            if (paymentService.isPaymentOTPExpired(emailRequest.getEmail())) {
                response.setMessage("Payment OTP is still valid");
            } else {
                response.setMessage("Payment OTP is not valid");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Check payment otp is expired failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/send-email-otp")
    public ResponseEntity<?> sendEmailVerification(@RequestParam Long participantId, @RequestBody EmailRequest emailRequest) {
        Response response = new Response();
        try {
            if (paymentService.sendEmailVerification(emailRequest.getEmail(), participantId)) {
                response.setMessage("Verification email has been sent");
            } else {
                response.setMessage("Bill verification is not valid");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Failed to send verification email: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }
}
