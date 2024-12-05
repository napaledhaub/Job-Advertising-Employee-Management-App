package com.finance.management.rest;

import com.finance.management.entity.AuthToken;
import com.finance.management.entity.Participant;
import com.finance.management.enumeration.ParticipantStatus;
import com.finance.management.service.*;
import com.finance.management.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private static final String SUCCESS = "Successfully registered. Please confirm through your email.";
    private static final String FAILED = "Failed to send confirmation email.";

    @Autowired
    private EmailService emailService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        try {
            String subject = "Registration Confirmation";
            String text = "Thank you for registering at our fitness center. Here is your OTP to activate your account:";
            String verificationCode = emailService.sendEmail(registerRequest.getEmail(), subject, text);
            registrationService.register(registerRequest, verificationCode);
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(FAILED);
        }

        return ResponseEntity.ok(SUCCESS);
    }

    @GetMapping("/check-status")
    public ResponseEntity<String> checkStatus(@RequestParam String email) {
        Optional<Participant> participantOptional = participantService.findByEmail(email);

        if (participantOptional.isPresent()) {
            Participant participant = participantOptional.get();

            if (participant.isVerified()) {
                return ResponseEntity.ok("Membership status: " + ParticipantStatus.REGISTERED.toString());
            } else {
                return ResponseEntity.ok("Membership status: " + ParticipantStatus.NOT_VALIDATED.toString());
            }
        }
        return ResponseEntity.ok("Membership status: " + ParticipantStatus.NOT_REGISTERED.toString());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = authenticationService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Authentication Failed: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authToken) {
        authenticationService.logout(authToken);
        return ResponseEntity.ok("Logged out successfully.");
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> login(@RequestHeader("Authorization") String authToken, @RequestBody VerificationRequest verificationRequest) {
        boolean validVerificationCode = registrationService.validateOtp(authToken, verificationRequest);
        if (validVerificationCode) {
            return ResponseEntity.ok("Your account has been successfully validated. Membership status: " + ParticipantStatus.REGISTERED.toString());
        }
        return ResponseEntity.ok("The OTP you entered is incorrect.");
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader("Authorization") String authToken) {
        AuthToken newAuthToken = authenticationService.refreshToken(authToken);
        if (newAuthToken != null) {
            return ResponseEntity.ok(newAuthToken);
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody EmailRequest emailRequest) {
        try {
            boolean isPasswordReset = passwordResetService.requestPasswordReset(emailRequest);

            if (isPasswordReset) {
                return ResponseEntity.ok("Password reset email has been successfully sent. Please check your email.");
            }

            return ResponseEntity.badRequest().body("Password reset email failed to send.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Password reset request failed to send: " + e.getMessage());
        }
    }

    @PostMapping("/forgot-password-confirmation")
    public ResponseEntity<?> forgotPasswordConfirmation(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        if (!forgotPasswordRequest.getNewPassword().equals(forgotPasswordRequest.getConfirmNewPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match.");
        }

        boolean isPasswordReset = passwordResetService.confirmPasswordReset(forgotPasswordRequest);

        if (isPasswordReset) {
            return ResponseEntity.ok("Password successfully changed.");
        }

        return ResponseEntity.badRequest().body("Password change failed, please check the token you entered.");
    }
}
