package com.finance.management.service;

import com.finance.management.dao.abstracts.CityRepository;
import com.finance.management.dao.abstracts.JobAdvertisementRepository;
import com.finance.management.entity.City;
import com.finance.management.entity.JobAdvertisement;
import com.finance.management.service.abstracts.JobAdvertisementService;
import com.finance.management.util.DataResult;
import com.finance.management.util.Result;
import com.finance.management.util.SuccessDataResult;
import com.finance.management.util.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdvertisementServiceImpl implements JobAdvertisementService {
    private JobAdvertisementRepository jobAdvertisementRepository;

    @Autowired
    public JobAdvertisementServiceImpl(JobAdvertisementRepository jobAdvertisementRepository) {
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }


    @Override
    public Result add(JobAdvertisement jobAdvertisement) {
        try {
            JobAdvertisement newJobAdvertisement = jobAdvertisementRepository.save(jobAdvertisement);
            return new SuccessResult(newJobAdvertisement.toString());
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAll() {
        try {
            return new SuccessDataResult<List<JobAdvertisement>>(jobAdvertisementRepository.findAll());
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
