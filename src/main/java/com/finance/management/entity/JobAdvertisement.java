package com.finance.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobPosition", "city", "employer"})
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_position_id")
    @JsonIgnore
    private JobPosition jobPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    @JsonIgnore
    private Employer employer;

    @Override
    public String toString() {
        return "JobAdvertisement{" +
                "advertisementId=" + advertisementId +
                ", openPositionCount=" + openPositionCount +
                ", description='" + description + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                ", jobReleaseDate=" + jobReleaseDate +
                ", applicationDeadline=" + applicationDeadline +
                ", jobPosition=" + (jobPosition != null ? jobPosition.getClass() : null) +
                ", city=" + (city != null ? city.getClass() : null) +
                ", employer=" + (employer != null ? employer.getClass() : null) +
                '}';
    }
}
