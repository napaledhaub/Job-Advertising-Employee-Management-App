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
            String verificationCode = emailService.generateVerificationCode();
            String subject = "Registration Confirmation";
            String text = "Thank you for registering at our fitness center. Here is your OTP to activate your account:" + verificationCode;
            emailService.sendEmail(registerRequest.getEmail(), subject, text);
            registrationService.register(registerRequest, verificationCode);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(FAILED);
        }
        return ResponseEntity.ok(SUCCESS);
    }

    @PostMapping("/check-status")
    public ResponseEntity<?> checkStatus(@RequestBody EmailRequest email) {
        Response response = new Response();
        try {
            Optional<Participant> participantOptional = participantService.findByEmail(email.getEmail());
            if (participantOptional.isPresent()) {
                Participant participant = participantOptional.get();
                if (participant.isVerified()) {
                    response.setMessage("Membership status: " + ParticipantStatus.REGISTERED.toString());
                } else {
                    response.setMessage("Membership status: " + ParticipantStatus.NOT_VALIDATED.toString());
                }
            }
            else {
                response.setMessage("Membership status: " + ParticipantStatus.NOT_REGISTERED.toString());
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Check status failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = new LoginResponse();
        try {
            response = authenticationService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Authentication failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authToken) {
        Response response = new Response();
        try {
            authenticationService.logout(authToken);
            response.setMessage("Logged out successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Logout failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> login(@RequestHeader("Authorization") String authToken, @RequestBody VerificationRequest verificationRequest) {
        Response response = new Response();
        try {
            if (registrationService.validateOtp(authToken, verificationRequest.getOtp())) {
                response.setMessage("Your account has been successfully validated. Membership status: " + ParticipantStatus.REGISTERED.toString());
            } else {
                response.setMessage("The OTP you entered is incorrect");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Confirm email failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader("Authorization") String authToken) {
        Response response = new Response();
        try {
            AuthToken newAuthToken = authenticationService.refreshToken(authToken);
            if (newAuthToken != null) {
                return ResponseEntity.ok(newAuthToken);
            }
            response.setMessage("Invalid or expired token");
        } catch (Exception e) {
            response.setMessage("Refresh failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody EmailRequest emailRequest) {
        Response response = new Response();
        try {
            if (passwordResetService.requestPasswordReset(emailRequest)) {
                response.setMessage("Password reset email has been successfully sent. Please check your email");
                return ResponseEntity.ok(response);
            }
            response.setMessage("Password reset email failed to send");
        } catch (Exception e) {
            response.setMessage("Password reset request failed to send: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/forgot-password-confirmation")
    public ResponseEntity<?> forgotPasswordConfirmation(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        Response response = new Response();
        try {
            if (passwordResetService.confirmPasswordReset(forgotPasswordRequest)) {
                response.setMessage("Password successfully changed");
                return ResponseEntity.ok(response);
            }
            response.setMessage("Password change failed, please check the token you entered");
        } catch (Exception e) {
            response.setMessage("Password change failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }
}
