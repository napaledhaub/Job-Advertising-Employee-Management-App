package com.finance.management.service;

import com.finance.management.dao.abstracts.CityRepository;
import com.finance.management.entity.City;
import com.finance.management.service.abstracts.CityService;
import com.finance.management.util.DataResult;
import com.finance.management.util.Result;
import com.finance.management.util.SuccessDataResult;
import com.finance.management.util.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Result add(City city) {
        try {
            City newCity = cityRepository.save(city);
            return new SuccessResult(newCity.toString());
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    @Override
    public DataResult<List<City>> getAll() {
        try {
            return new SuccessDataResult<List<City>>(cityRepository.findAll());
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}
