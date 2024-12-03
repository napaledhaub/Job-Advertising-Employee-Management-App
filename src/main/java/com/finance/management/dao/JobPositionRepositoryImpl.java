package com.finance.management.dao;

import com.finance.management.dao.abstracts.JobPositionRepository;
import com.finance.management.dao.abstracts.JobSeekerRepository;
import com.finance.management.entity.City;
import com.finance.management.entity.JobPosition;
import com.finance.management.entity.JobSeeker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JobPositionRepositoryImpl implements JobPositionRepository {
    private EntityManager entityManager;

    @Autowired
    public JobPositionRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<JobPosition> findAll() {
        try {
            TypedQuery<JobPosition> query = entityManager.createQuery("from JobPosition", JobPosition.class);
            return query.getResultList();
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    @Transactional
    @Override
    public JobPosition save(JobPosition jobPosition) {
        try {
            return entityManager.merge(jobPosition);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
