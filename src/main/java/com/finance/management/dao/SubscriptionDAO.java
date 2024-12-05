package com.finance.management.dao;

import com.finance.management.entity.Participant;
import com.finance.management.entity.ServiceMenu;
import com.finance.management.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionDAO extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByParticipantAndServiceMenu(Participant participant, ServiceMenu serviceMenu);

    List<Subscription> findAllByParticipant(Participant participant);
}
