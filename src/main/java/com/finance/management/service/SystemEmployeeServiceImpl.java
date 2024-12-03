package com.finance.management.service;

import com.finance.management.dao.abstracts.CityRepository;
import com.finance.management.dao.abstracts.SystemEmployeeRepository;
import com.finance.management.entity.City;
import com.finance.management.entity.SystemEmployee;
import com.finance.management.service.abstracts.SystemEmployeeService;
import com.finance.management.util.DataResult;
import com.finance.management.util.Result;
import com.finance.management.util.SuccessDataResult;
import com.finance.management.util.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemEmployeeServiceImpl implements SystemEmployeeService {
    private SystemEmployeeRepository systemEmployeeRepository;

    @Autowired
    public SystemEmployeeServiceImpl(SystemEmployeeRepository systemEmployeeRepository) {
        this.systemEmployeeRepository = systemEmployeeRepository;
    }

    @Override
    public Result add(SystemEmployee systemEmployee) {
        try {
            SystemEmployee newSystemEmployee = systemEmployeeRepository.save(systemEmployee);
            return new SuccessResult(newSystemEmployee.toString());
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    @Override
    public DataResult<List<SystemEmployee>> getAll() {
        try {
            return new SuccessDataResult<List<SystemEmployee>>(systemEmployeeRepository.findAll());
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
