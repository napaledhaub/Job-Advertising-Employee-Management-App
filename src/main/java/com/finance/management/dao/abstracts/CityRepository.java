package com.finance.management.dao.abstracts;

import com.finance.management.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CityRepository {
    List<City> findAll();

    City save(City city);
}
