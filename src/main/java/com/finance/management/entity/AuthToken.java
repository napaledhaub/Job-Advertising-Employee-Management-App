package com.finance.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auth_token")
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="token")
    private String token;

    private LocalDateTime expirationDateTime;

    @OneToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    public AuthToken(String token, LocalDateTime expirationDateTime, Participant participant) {
        this.token = token;
        this.expirationDateTime = expirationDateTime;
        this.participant = participant;
    }

    public void updateExpiration() {
        this.expirationDateTime = LocalDateTime.now().plusHours(1);
    }
}
