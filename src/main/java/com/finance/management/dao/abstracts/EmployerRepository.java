package com.finance.management.dao.abstracts;

import com.finance.management.entity.City;
import com.finance.management.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface EmployerRepository {
    List<Employer> findAll();

    Employer save(Employer employer);
}
