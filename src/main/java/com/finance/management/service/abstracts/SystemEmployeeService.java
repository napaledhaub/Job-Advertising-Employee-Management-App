package com.finance.management.service.abstracts;

import com.finance.management.entity.JobSeeker;
import com.finance.management.entity.SystemEmployee;
import com.finance.management.util.DataResult;
import com.finance.management.util.Result;

import java.util.List;

public interface SystemEmployeeService {
    Result add(SystemEmployee systemEmployee);
    DataResult<List<SystemEmployee>> getAll();
}
