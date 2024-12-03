package com.finance.management.service;

import com.finance.management.dao.abstracts.CityRepository;
import com.finance.management.dao.abstracts.JobPositionRepository;
import com.finance.management.entity.City;
import com.finance.management.entity.JobPosition;
import com.finance.management.service.abstracts.JobPositionService;
import com.finance.management.util.DataResult;
import com.finance.management.util.Result;
import com.finance.management.util.SuccessDataResult;
import com.finance.management.util.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionServiceImpl implements JobPositionService {
    private JobPositionRepository jobPositionRepository;

    @Autowired
    public JobPositionServiceImpl(JobPositionRepository jobPositionRepository) {
        this.jobPositionRepository = jobPositionRepository;
    }

    @Override
    public Result add(JobPosition jobPosition) {
        try {
            JobPosition newJobPosition = jobPositionRepository.save(jobPosition);
            return new SuccessResult(newJobPosition.toString());
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    @Override
    public DataResult<List<JobPosition>> getAll() {
        try {
            return new SuccessDataResult<List<JobPosition>>(jobPositionRepository.findAll());
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
