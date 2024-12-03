package com.finance.management.service.abstracts;

import com.finance.management.entity.City;
import com.finance.management.entity.JobPosition;
import com.finance.management.util.DataResult;
import com.finance.management.util.Result;

import java.util.List;

public interface JobPositionService {
    Result add(JobPosition jobPosition);
    DataResult<List<JobPosition>> getAll();
}
