package com.finance.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_seeker")
public class JobSeeker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_seeker_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "re_password")
    private String rePassword;
}
