package com.finance.management.service;

import com.finance.management.dao.AuthTokenDAO;
import com.finance.management.dao.ParticipantDAO;
import com.finance.management.dao.ServiceMenuDAO;
import com.finance.management.dao.SubscriptionDAO;
import com.finance.management.entity.AuthToken;
import com.finance.management.entity.Participant;
import com.finance.management.entity.ServiceMenu;
import com.finance.management.entity.Subscription;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionDAO subscriptionDAO;

    @Autowired
    private AuthTokenDAO authTokenDAO;

    @Autowired
    private ParticipantDAO participantDAO;

    @Autowired
    private ServiceMenuDAO serviceMenuDAO;

    public List<Subscription> getSubscriptionList(String token) {
        AuthToken authToken = authTokenDAO.findByToken(token);
        if (authToken != null) {
            Participant participant = authToken.getParticipant();
            return subscriptionDAO.findAllByParticipant(participant);
        } else {
            throw new EntityNotFoundException("Participant not found");
        }
    }

    public Subscription subscribeToService(String token, Long serviceMenuId) {
        AuthToken authToken = authTokenDAO.findByToken(token);
        if (authToken != null) {
            Participant participant = authToken.getParticipant();
            ServiceMenu serviceMenu = serviceMenuDAO.findById(serviceMenuId)
                    .orElseThrow(() -> new EntityNotFoundException("ServiceMenu not found with ID: " + serviceMenuId));

            Optional<Subscription> existingSubscription = subscriptionDAO.findByParticipantAndServiceMenu(participant, serviceMenu);

            Subscription subscription;
            subscription = existingSubscription.orElseGet(Subscription::new);
            subscription.setParticipant(participant);
            subscription.setServiceMenu(serviceMenu);
            subscription.setStartDate(LocalDateTime.now());
            subscription.setEndDate(LocalDateTime.now().plusDays(30));
            subscription.setRemainingSessions(serviceMenu.getTotalSessions());

            participant.setBillAmount(BigDecimal.valueOf(serviceMenu.getPricePerSession() * serviceMenu.getTotalSessions()));
            participant.setBillVerified(false);
            participantDAO.save(participant);
            return subscriptionDAO.save(subscription);
        } else {
            throw new EntityNotFoundException("Participant not found");
        }
    }

    public void cancelSubscription(String token, Long subscriptionId) {
        AuthToken authToken = authTokenDAO.findByToken(token);
        if (authToken != null) {
            Optional<Subscription> subscriptionOptional = subscriptionDAO.findById(subscriptionId);
            if (subscriptionOptional.isPresent()) {
                Subscription subscription = subscriptionOptional.get();
                Participant participant = authToken.getParticipant();
                ServiceMenu serviceMenu = subscription.getServiceMenu();

                BigDecimal currentBillAmount = participant.getBillAmount() != null ? participant.getBillAmount() : BigDecimal.ZERO;
                BigDecimal pricePerSession = BigDecimal.valueOf(serviceMenu.getPricePerSession());
                BigDecimal remainingSessions = BigDecimal.valueOf(subscription.getRemainingSessions());
                BigDecimal deductionAmount = pricePerSession.multiply(remainingSessions);
                BigDecimal newBillAmount = currentBillAmount.subtract(deductionAmount);
                if (newBillAmount.compareTo(BigDecimal.ZERO) < 0) {
                    newBillAmount = BigDecimal.ZERO;
                }

                participant.setBillAmount(newBillAmount);
                participantDAO.save(participant);
                subscriptionDAO.delete(subscriptionOptional.get());
            } else {
                throw new EntityNotFoundException("Subscription code not found");
            }
        } else {
            throw new EntityNotFoundException("Participant not found");
        }
    }

    public void extendSubscription(String token, Long subscriptionId) {
        AuthToken authToken = authTokenDAO.findByToken(token);
        if (authToken != null) {
            Optional<Subscription> subscriptionOptional = subscriptionDAO.findById(subscriptionId);
            if (subscriptionOptional.isPresent()) {
                Subscription subscription = subscriptionOptional.get();
                ServiceMenu serviceMenu = subscription.getServiceMenu();
                Participant participant = subscription.getParticipant();

                subscription.setEndDate(subscription.getEndDate().plusDays(serviceMenu.getTotalSessions()));
                subscription.setRemainingSessions(subscription.getRemainingSessions() + serviceMenu.getTotalSessions());
                subscriptionDAO.save(subscription);

                BigDecimal pricePerSession = BigDecimal.valueOf(serviceMenu.getPricePerSession());
                BigDecimal totalSessions = BigDecimal.valueOf(serviceMenu.getTotalSessions());
                BigDecimal currentBillAmount = participant.getBillAmount() != null ? participant.getBillAmount() : BigDecimal.ZERO;
                BigDecimal newBillAmount = pricePerSession.multiply(totalSessions).add(currentBillAmount);

                participant.setBillAmount(newBillAmount);
                participant.setBillVerified(false);
                participantDAO.save(participant);
            } else {
                throw new EntityNotFoundException("Subscription not found");
            }
        } else {
            throw new EntityNotFoundException("Participant not found");
        }
    }
}
