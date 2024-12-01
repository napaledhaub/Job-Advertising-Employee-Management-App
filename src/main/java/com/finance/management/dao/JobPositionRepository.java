package com.finance.management.dao;

import com.finance.management.entity.City;
import com.finance.management.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "job_position")
public interface JobPositionRepository extends JpaRepository<JobPosition, Integer> {
}
