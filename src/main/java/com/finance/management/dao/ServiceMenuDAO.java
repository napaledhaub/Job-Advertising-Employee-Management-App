package com.finance.management.dao;

import com.finance.management.entity.ServiceMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceMenuDAO extends JpaRepository<ServiceMenu, Long> {
}
