package com.finance.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_menu")
@JsonIgnoreProperties({"serviceMenu"})
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonProperty("duration_in_minutes")
    private int durationInMinutes;

    private String description;

    @ManyToOne
    private ServiceMenu serviceMenu;
}
