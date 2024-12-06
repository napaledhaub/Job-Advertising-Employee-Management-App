package com.finance.management.service;

import com.finance.management.dao.AuthTokenDAO;
import com.finance.management.dao.ParticipantDAO;
import com.finance.management.entity.AuthToken;
import com.finance.management.entity.Participant;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class PaymentService {
    @Autowired
    private ParticipantDAO participantDAO;
    @Autowired
    private AuthTokenDAO authTokenDAO;
    @Autowired
    private EmailService emailService;

    public boolean verifyPaymentOTP(String email, String otp) {
        Optional<Participant> participantOptional = participantDAO.findByEmail(email);

        if (participantOptional.isPresent()) {
            Participant participant = participantOptional.get();

            if (participant.getPaymentOTP() != null &&
                    participant.getPaymentOTP().equals(otp) &&
                    LocalDateTime.now().isBefore(participant.getPaymentOTPExpiration())) {
                participant.setPaymentOTP(null);
                participant.setPaymentOTPExpiration(null);
                participant.setBillAmount(BigDecimal.valueOf(0));
                participant.setBillVerified(false);
                participantDAO.save(participant);
                return true;
            }
        }

        return false;
    }

    public boolean verifyBillAmount(String token, BigDecimal expectedBillAmount) {
        AuthToken authToken = authTokenDAO.findByToken(token);
        if (authToken != null) {
            Participant participant = authToken.getParticipant();

            if (participant.getBillAmount() != null && participant.getBillAmount().compareTo(expectedBillAmount) == 0) {
                participant.setBillVerified(true);
                participantDAO.save(participant);
                return true;
            }
        } else {
            throw new EntityNotFoundException("Participant not found.");
        }

        return false;
    }


    public boolean isPaymentOTPExpired(String email) {
        Optional<Participant> participantOptional = participantDAO.findByEmail(email);

        if (participantOptional.isPresent()) {
            Participant participant = participantOptional.get();
            return participant.getPaymentOTPExpiration() != null &&
                    LocalDateTime.now().isAfter(participant.getPaymentOTPExpiration());
        }

        return false;
    }

    private String generateVerificationCode() {
        int codeLength = 6;
        String allowedChars = "0123456789";

        Random random = new Random();
        StringBuilder verificationCode = new StringBuilder(codeLength);

        for (int i = 0; i < codeLength; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            verificationCode.append(randomChar);
        }

        return verificationCode.toString();
    }

    public void sendEmailVerification(String email, Long participantId) {
        String code = generateVerificationCode();

        Optional<Participant> participantOptional = participantDAO.findById(participantId);

        if (participantOptional.isPresent()) {
            Participant participant = participantOptional.get();
            participant.setPaymentOTP(code);
            participant.setPaymentOTPExpiration(LocalDateTime.now().plusMinutes(15));
            participantDAO.save(participant);
        }

        String subject = "OTP Code";
        String text = "Your OTP code is: " + code;
        emailService.sendEmail(email, subject, text);
    }
}
