package com.finance.management.service.abstracts;

import com.finance.management.entity.City;
import com.finance.management.entity.Employer;
import com.finance.management.util.DataResult;
import com.finance.management.util.Result;

import java.util.List;

public interface EmployerService {
    Result add(Employer employer);
    DataResult<List<Employer>> getAll();
}
