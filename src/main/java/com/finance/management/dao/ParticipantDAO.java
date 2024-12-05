package com.finance.management.dao;

import com.finance.management.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantDAO extends JpaRepository<Participant, Long> {
    Optional<Participant> findByEmail(String email);

    Optional<Participant> findByResetToken(String resetToken);
}
