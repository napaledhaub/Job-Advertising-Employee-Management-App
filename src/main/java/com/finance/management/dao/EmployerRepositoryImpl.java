package com.finance.management.dao;

import com.finance.management.dao.abstracts.EmployerRepository;
import com.finance.management.entity.Employer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployerRepositoryImpl implements EmployerRepository {
    private EntityManager entityManager;

    @Autowired
    public EmployerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employer> findAll() {
        try {
            TypedQuery<Employer> query = entityManager.createQuery("from Employer", Employer.class);
            return query.getResultList();
        }
            catch(Exception ex) {
            throw ex;
        }
    }

    @Transactional
    @Override
    public Employer save(Employer employer) {
        try {
            return entityManager.merge(employer);
        }
            catch(Exception ex) {
            throw ex;
        }
    }
}
