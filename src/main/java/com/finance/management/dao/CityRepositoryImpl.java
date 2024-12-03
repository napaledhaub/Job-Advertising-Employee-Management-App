package com.finance.management.dao;

import com.finance.management.dao.abstracts.CityRepository;
import com.finance.management.entity.City;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CityRepositoryImpl implements CityRepository {
    private EntityManager entityManager;

    @Autowired
    public CityRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<City> findAll() {
        try {
            TypedQuery<City> query = entityManager.createQuery("from City", City.class);
            return query.getResultList();
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    @Transactional
    @Override
    public City save(City city) {
        try{
            return entityManager.merge(city);
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
