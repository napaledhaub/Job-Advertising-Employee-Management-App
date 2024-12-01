package com.finance.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "system_employee")
public class SystemEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "system_employee_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;
}
