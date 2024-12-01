package com.finance.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="city")
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","job_advertisements","jobAdvertisements"})
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="city_id")
    private int id;

    @Column(name="city_name")
    private String cityName;

    @OneToMany(mappedBy = "city")
    private List<JobAdvertisement> jobAdvertisements;
}
