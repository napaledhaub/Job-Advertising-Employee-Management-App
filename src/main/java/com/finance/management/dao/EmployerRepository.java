package com.finance.management.dao;

import com.finance.management.entity.City;
import com.finance.management.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "employer")
public interface EmployerRepository extends JpaRepository<Employer, Integer> {
}
