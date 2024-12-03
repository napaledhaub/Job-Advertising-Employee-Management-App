package com.finance.management.service.abstracts;

import com.finance.management.entity.City;
import com.finance.management.entity.JobAdvertisement;
import com.finance.management.util.DataResult;
import com.finance.management.util.Result;

import java.util.List;

public interface JobAdvertisementService {
    Result add(JobAdvertisement jobAdvertisement);
    DataResult<List<JobAdvertisement>> getAll();
}
