package com.finance.management.service.abstracts;

import com.finance.management.entity.City;
import com.finance.management.util.DataResult;
import com.finance.management.util.Result;

import java.util.List;

public interface CityService {
    Result add(City city);
    DataResult<List<City>> getAll();
}
