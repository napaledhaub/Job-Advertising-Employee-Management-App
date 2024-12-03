package com.finance.management.dao;

import com.finance.management.dao.abstracts.JobSeekerRepository;
import com.finance.management.entity.City;
import com.finance.management.entity.JobSeeker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JobSeekerRepositoryImpl implements JobSeekerRepository {
    private EntityManager entityManager;

    @Autowired
    public JobSeekerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<JobSeeker> findAll() {
        try {
            TypedQuery<JobSeeker> query = entityManager.createQuery("from JobSeeker", JobSeeker.class);
            return query.getResultList();
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    @Transactional
    @Override
    public JobSeeker save(JobSeeker jobSeeker) {
        try {
            return entityManager.merge(jobSeeker);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
