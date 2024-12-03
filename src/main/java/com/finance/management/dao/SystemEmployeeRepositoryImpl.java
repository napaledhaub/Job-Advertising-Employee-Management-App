package com.finance.management.dao;

import com.finance.management.dao.abstracts.SystemEmployeeRepository;
import com.finance.management.entity.City;
import com.finance.management.entity.SystemEmployee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SystemEmployeeRepositoryImpl implements SystemEmployeeRepository {
    private EntityManager entityManager;

    @Autowired
    public SystemEmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<SystemEmployee> findAll() {
        try {
            TypedQuery<SystemEmployee> query = entityManager.createQuery("from SystemEmployee", SystemEmployee.class);
            return query.getResultList();
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    @Transactional
    @Override
    public SystemEmployee save(SystemEmployee systemEmployee) {
        try {
            return entityManager.merge(systemEmployee);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
