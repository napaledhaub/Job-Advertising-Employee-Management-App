package com.finance.management.service;

import com.finance.management.dao.AuthTokenDAO;
import com.finance.management.dao.ParticipantDAO;
import com.finance.management.entity.AuthToken;
import com.finance.management.entity.Participant;
import com.finance.management.util.CreditCardResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Optional;

@Service
public class ParticipantService {
    @Autowired
    private AuthTokenDAO authTokenDAO;
    @Autowired
    private ParticipantDAO participantDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Participant> findByEmail(String email) {
        return participantDAO.findByEmail(email);
    }

    public void updateFullName(String token, String newFullName) {
        AuthToken authToken = authTokenDAO.findByToken(token);
        if (authToken != null) {
            Participant participant = authToken.getParticipant();
            participant.setName(newFullName);
            participantDAO.save(participant);
        } else {
            throw new EntityNotFoundException("Participant not found.");
        }
    }

    public void updateCreditCardInfo(String token, CreditCardResponse creditCard) {
        AuthToken authToken = authTokenDAO.findByToken(token);
        if (authToken != null) {
            Participant participant = authToken.getParticipant();
            String creditCardInfo = encryptCreditCardInfo(creditCard.getCardNo() +
                    creditCard.getCvv() +
                    creditCard.getExpiredDate() +
                    creditCard.getOwnerName());
            String encryptedCreditCardInfo = encryptCreditCardInfo(creditCardInfo);
            participant.setCreditCardInfo(encryptedCreditCardInfo);
            participantDAO.save(participant);
        } else {
            throw new EntityNotFoundException("Participant not found.");
        }
    }

    public void updatePassword(String token, String oldPassword, String newPassword) {
        AuthToken authToken = authTokenDAO.findByToken(token);
        if (authToken != null) {
            Participant participant = authToken.getParticipant();
            if (passwordEncoder.matches(oldPassword, participant.getPassword())) {
                String encryptedPassword = passwordEncoder.encode(newPassword);
                participant.setPassword(encryptedPassword);
                participantDAO.save(participant);
            }
            else {
                throw new RuntimeException("Old password not match.");
            }
        } else {
            throw new EntityNotFoundException("Participant not found.");
        }
    }

    private String encryptCreditCardInfo(String creditCardInfo) {
        try {
            SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedData = cipher.doFinal(creditCardInfo.getBytes());

            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
