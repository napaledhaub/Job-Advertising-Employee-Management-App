package com.finance.management.rest;

import com.finance.management.entity.City;
import com.finance.management.entity.Employer;
import com.finance.management.service.abstracts.CityService;
import com.finance.management.util.DataResult;
import com.finance.management.util.ErrorDataResult;
import com.finance.management.util.ErrorResult;
import com.finance.management.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {
    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add_city")
    public Result add(@RequestBody City city) {
        try {
            return this.cityService.add(city);
        }
        catch(Exception ex) {
            return new ErrorResult(ex.toString());
        }
    }

    @GetMapping("/get_all_city")
    public DataResult<List<City>> getAll(){
        try {
            return this.cityService.getAll();
        }
        catch(Exception ex) {
            return new ErrorDataResult<List<City>>(ex.toString());
        }
    }
}
