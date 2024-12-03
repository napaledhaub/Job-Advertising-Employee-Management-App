package com.finance.management.service;

import com.finance.management.dao.abstracts.CityRepository;
import com.finance.management.dao.abstracts.JobSeekerRepository;
import com.finance.management.entity.City;
import com.finance.management.entity.JobSeeker;
import com.finance.management.service.abstracts.JobSeekerService;
import com.finance.management.util.DataResult;
import com.finance.management.util.Result;
import com.finance.management.util.SuccessDataResult;
import com.finance.management.util.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    public JobSeekerServiceImpl(JobSeekerRepository jobSeekerRepository) {
        this.jobSeekerRepository = jobSeekerRepository;
    }

    @Override
    public Result add(JobSeeker jobSeeker) {
        try {
            JobSeeker newJobSeeker = jobSeekerRepository.save(jobSeeker);
            return new SuccessResult(newJobSeeker.toString());
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    @Override
    public DataResult<List<JobSeeker>> getAll() {
        try {
            return new SuccessDataResult<List<JobSeeker>>(jobSeekerRepository.findAll());
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
