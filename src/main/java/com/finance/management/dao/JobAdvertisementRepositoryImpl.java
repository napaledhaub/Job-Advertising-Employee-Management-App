package com.finance.management.dao;

import com.finance.management.dao.abstracts.JobAdvertisementRepository;
import com.finance.management.entity.City;
import com.finance.management.entity.JobAdvertisement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JobAdvertisementRepositoryImpl implements JobAdvertisementRepository {
    private EntityManager entityManager;

    @Autowired
    public JobAdvertisementRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<JobAdvertisement> findAll() {
        try {
            TypedQuery<JobAdvertisement> query = entityManager.createQuery("SELECT ja FROM JobAdvertisement ja", JobAdvertisement.class);
            return query.getResultList();
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    @Transactional
    @Override
    public JobAdvertisement save(JobAdvertisement jobAdvertisement) {
        try {
            return entityManager.merge(jobAdvertisement);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
