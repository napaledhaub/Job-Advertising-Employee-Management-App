package com.finance.management.rest;

import com.finance.management.entity.City;
import com.finance.management.entity.JobAdvertisement;
import com.finance.management.service.abstracts.CityService;
import com.finance.management.service.abstracts.JobAdvertisementService;
import com.finance.management.util.DataResult;
import com.finance.management.util.ErrorDataResult;
import com.finance.management.util.ErrorResult;
import com.finance.management.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job_advertisement")
public class JobAdvertisementController {
    private JobAdvertisementService jobAdvertisementService;

    @Autowired
    public JobAdvertisementController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @PostMapping("/add_ads")
    public Result add(@RequestBody JobAdvertisement jobAdvertisement) {
        try {
            return this.jobAdvertisementService.add(jobAdvertisement);
        }
        catch(Exception ex) {
            return new ErrorResult(ex.toString());
        }
    }

    @GetMapping("/get_all_ads")
    public DataResult<List<JobAdvertisement>> getAll(){
        try {
            return this.jobAdvertisementService.getAll();
        }
        catch(Exception ex) {
            return new ErrorDataResult<List<JobAdvertisement>>(ex.toString());
        }
    }
}
