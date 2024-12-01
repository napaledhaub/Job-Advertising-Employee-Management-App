package com.finance.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_advertisement")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class JobAdvertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_advertisement_id")
    private int advertisementId;

    @Column(name = "open_position_count")
    private int openPositionCount;

    @Column(name = "description")
    private String description;

    @Column(name = "min_salary")
    private int minSalary;

    @Column(name = "max_salary")
    private int maxSalary;

    @Column(name = "job_release_date")
    private LocalDate jobReleaseDate;

    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

    @ManyToOne
    @JoinColumn(name = "job_position_id")
    private JobPosition jobPosition;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
