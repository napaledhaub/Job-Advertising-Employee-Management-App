package com.finance.management.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_menu")
@EqualsAndHashCode
public class ServiceMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonProperty("price_per_session")
    private double pricePerSession;

    @JsonProperty("total_sessions")
    private int totalSessions;

    private String schedule;

    @JsonProperty("duration_in_minutes")
    private int durationInMinutes;

    @JsonProperty("exercise_list")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceMenu")
    private List<Exercise> exerciseList;
}
