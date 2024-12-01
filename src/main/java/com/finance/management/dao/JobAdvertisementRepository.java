package com.finance.management.dao;

import com.finance.management.entity.City;
import com.finance.management.entity.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "job_advertisement")
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement, Integer> {
}
