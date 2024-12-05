package com.finance.management.service;

import com.finance.management.dao.AuthTokenDAO;
import com.finance.management.dao.ParticipantDAO;
import com.finance.management.entity.AuthToken;
import com.finance.management.entity.Participant;
import com.finance.management.util.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
    @Autowired
    private ParticipantDAO participantDAO;
    @Autowired
    private AuthTokenDAO authTokenDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(String email, String password) {
        Optional<Participant> participantOptional = participantDAO.findByEmail(email);

        LoginResponse loginResponse = new LoginResponse();
        if (participantOptional.isPresent()) {
            Participant participant = participantOptional.get();
            if (passwordEncoder.matches(password, participant.getPassword())) {
                AuthToken authToken = generateAuthToken(participant);
                authTokenDAO.save(authToken);
                loginResponse.setToken(authToken.getToken());
                loginResponse.setMessage("Login Successful");
                return loginResponse;
            }
            loginResponse.setMessage("Incorrect email or password.");
        }
        loginResponse.setMessage("Incorrect email or password.");

        return loginResponse;
    }

    public void logout(String token) {
        authTokenDAO.deleteByToken(token);
    }

    public AuthToken refreshToken(String token) {
        AuthToken existingToken = authTokenDAO.findByToken(token);

        if (existingToken != null) {
            existingToken.updateExpiration();
            authTokenDAO.save(existingToken);
            return existingToken;
        }
        return null;
    }

    private AuthToken generateAuthToken(Participant participant) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expirationDateTime = LocalDateTime.now().plusHours(1);
        AuthToken authToken = authTokenDAO.findByParticipant(participant);
        if (authToken != null) {
            authToken.setToken(token);
            authToken.setExpirationDateTime(expirationDateTime);
            return authToken;
        }
        return new AuthToken(token, expirationDateTime, participant);
    }
}
