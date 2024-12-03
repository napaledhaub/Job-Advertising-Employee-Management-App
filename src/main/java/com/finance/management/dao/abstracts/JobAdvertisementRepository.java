package com.finance.management.dao.abstracts;

import com.finance.management.entity.City;
import com.finance.management.entity.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface JobAdvertisementRepository {
    List<JobAdvertisement> findAll();

    JobAdvertisement save(JobAdvertisement jobAdvertisement);
}
