package com.finance.management.dao.abstracts;

import com.finance.management.entity.City;
import com.finance.management.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface JobSeekerRepository {
    List<JobSeeker> findAll();

    JobSeeker save(JobSeeker jobSeeker);
}
