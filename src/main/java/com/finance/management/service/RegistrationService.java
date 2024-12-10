package com.finance.management.service;

import com.finance.management.dao.AuthTokenDAO;
import com.finance.management.dao.ParticipantDAO;
import com.finance.management.entity.AuthToken;
import com.finance.management.entity.Participant;
import com.finance.management.util.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

@Service
public class RegistrationService {
    @Autowired
    private AuthTokenDAO authTokenDAO;
    @Autowired
    private ParticipantDAO participantDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request, String verificationCode) {
        if (participantDAO.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use");
        }

        Participant participant = new Participant();
        participant.setName(request.getName());
        participant.setEmail(request.getEmail());
        participant.setPassword(passwordEncoder.encode(request.getPassword()));
        participant.setPhoneNumber(request.getPhoneNumber());
        participant.setVerified(false);
        participant.setVerificationCode(verificationCode);

        String creditCardInfo = encryptCreditCardInfo(request.getCreditCard().getCardNo() +
                request.getCreditCard().getCvv() +
                request.getCreditCard().getExpiredDate() +
                request.getCreditCard().getOwnerName());

        participant.setCreditCardInfo(creditCardInfo);
        participantDAO.save(participant);

        return verificationCode;
    }

    public boolean validateOtp(String token, String verificationRequest) {
        AuthToken authToken = authTokenDAO.findByToken(token);
        if (authToken != null) {
            Participant participant = authToken.getParticipant();
            if (verificationRequest.equals(participant.getVerificationCode())) {
                participant.setVerified(true);
                participant.setVerificationCode(null);
                participantDAO.save(participant);
                return true;
            }
        }
        return false;
    }

    private String encryptCreditCardInfo(String creditCardInfo) {
        try {
            SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedData = cipher.doFinal(creditCardInfo.getBytes());

            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
