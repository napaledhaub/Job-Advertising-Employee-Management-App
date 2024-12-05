package com.finance.management.service;

import com.finance.management.dao.ParticipantDAO;
import com.finance.management.entity.Participant;
import com.finance.management.util.EmailRequest;
import com.finance.management.util.ForgotPasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
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

    public boolean requestPasswordReset(EmailRequest dtoEmailRequest) {
        Optional<Participant> participantOptional = participantDAO.findByEmail(dtoEmailRequest.getEmail());

        if (participantOptional.isPresent()) {
            Participant participant = participantOptional.get();

            String resetToken = generateResetToken();

            participant.setResetToken(resetToken);

            participantDAO.save(participant);

            sendPasswordResetEmail(participant, resetToken);

            return true;
        }
        return false;
    }

    public boolean confirmPasswordReset(ForgotPasswordRequest dtoForgotPasswordRequest) {
        Optional<Participant> participantOptional = participantDAO.findByResetToken(dtoForgotPasswordRequest.getResetToken());

        if (participantOptional.isPresent()) {
            Participant participant = participantOptional.get();
            participant.setResetToken(null);
            participant.setPassword(passwordEncoder.encode(dtoForgotPasswordRequest.getNewPassword()));
            participantDAO.save(participant);
            return true;
        }
        return false;
    }

    private String generateResetToken() {
        return UUID.randomUUID().toString();
    }

    private void sendPasswordResetEmail(Participant participant, String resetToken) {
        String subject = "Reset Password";
        String text = "You have requested a password reset. Here is the token to reset your password: ".concat(resetToken);

        try {
            emailService.sendEmailPasswordReset(participant.getEmail(), subject, text);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
