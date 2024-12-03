package com.finance.management.service;

import com.finance.management.dao.abstracts.CityRepository;
import com.finance.management.dao.abstracts.EmployerRepository;
import com.finance.management.entity.City;
import com.finance.management.entity.Employer;
import com.finance.management.service.abstracts.EmployerService;
import com.finance.management.util.DataResult;
import com.finance.management.util.Result;
import com.finance.management.util.SuccessDataResult;
import com.finance.management.util.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {
    private EmployerRepository employerRepository;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Override
    public Result add(Employer employer) {
        try {
            Employer newEmployer = employerRepository.save(employer);
            return new SuccessResult(newEmployer.toString());
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        try {
            return new SuccessDataResult<List<Employer>>(employerRepository.findAll());
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
