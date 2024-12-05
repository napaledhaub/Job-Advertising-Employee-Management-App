package com.finance.management.dao;

import com.finance.management.entity.AuthToken;
import com.finance.management.entity.Participant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenDAO extends JpaRepository<AuthToken, Long> {
    AuthToken findByToken(String token);

    @Transactional
    void deleteByToken(String token);

    AuthToken findByParticipant(Participant participant);
}
