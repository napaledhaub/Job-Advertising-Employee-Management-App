package com.finance.management.dao;

import com.finance.management.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "city")
public interface CityRepository extends JpaRepository<City, Integer> {
}
