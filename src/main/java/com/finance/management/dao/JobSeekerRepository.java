package com.finance.management.dao;

import com.finance.management.entity.City;
import com.finance.management.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "job_seeker")
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Integer> {
}
