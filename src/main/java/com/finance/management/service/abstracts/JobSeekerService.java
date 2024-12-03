package com.finance.management.service.abstracts;

import com.finance.management.entity.JobPosition;
import com.finance.management.entity.JobSeeker;
import com.finance.management.util.DataResult;
import com.finance.management.util.Result;

import java.util.List;

public interface JobSeekerService {
    Result add(JobSeeker jobSeeker);
    DataResult<List<JobSeeker>> getAll();
}
