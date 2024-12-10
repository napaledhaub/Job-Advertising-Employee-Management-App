package com.finance.management.service;

import com.finance.management.dao.ParticipantDAO;
import com.finance.management.entity.Participant;
import com.finance.management.util.EmailRequest;
import com.finance.management.util.ForgotPasswordRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {
    @Autowired
    private ParticipantDAO participantDAO;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean requestPasswordReset(EmailRequest emailRequest) {
        Optional<Participant> participantOptional = participantDAO.findByEmail(emailRequest.getEmail());
        if (participantOptional.isPresent()) {
            String resetToken = UUID.randomUUID().toString();

            Participant participant = participantOptional.get();
            participant.setResetToken(resetToken);
            participantDAO.save(participant);

            String subject = "Reset Password";
            String text = "You have requested a password reset. Here is the token to reset your password: ".concat(resetToken);
            emailService.sendEmail(participant.getEmail(), subject, text);
            return true;
        } else {
            throw new EntityNotFoundException("Participant not found");
        }
    }

    public boolean confirmPasswordReset(ForgotPasswordRequest forgotPasswordRequest) {
        Optional<Participant> participantOptional = participantDAO.findByResetToken(forgotPasswordRequest.getResetToken());
        if (participantOptional.isPresent()) {
            Participant participant = participantOptional.get();
            participant.setResetToken(null);
            participant.setPassword(passwordEncoder.encode(forgotPasswordRequest.getNewPassword()));
            participantDAO.save(participant);
            return true;
        } else {
            throw new EntityNotFoundException("Participant not found");
        }
    }
}
