package com.finance.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employer")
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_id")
    private int id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_web_page")
    private String companyWebPage;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "re_password", nullable = false)
    private String rePassword;

    @OneToMany(mappedBy = "employer")
    private List<JobAdvertisement> jobAdvertisements;
}
