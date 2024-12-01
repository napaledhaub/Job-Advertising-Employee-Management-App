package com.finance.management.dao;

import com.finance.management.entity.SystemEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "system_employee")
public interface SystemEmployeeRepository extends JpaRepository<SystemEmployee, Integer> {
}
