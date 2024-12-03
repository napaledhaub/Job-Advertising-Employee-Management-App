package com.finance.management.rest;

import com.finance.management.entity.City;
import com.finance.management.entity.JobPosition;
import com.finance.management.service.abstracts.CityService;
import com.finance.management.service.abstracts.JobPositionService;
import com.finance.management.util.DataResult;
import com.finance.management.util.ErrorDataResult;
import com.finance.management.util.ErrorResult;
import com.finance.management.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job_position")
public class JobPositionController {
    private JobPositionService jobPositionService;

    @Autowired
    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @PostMapping("/add_position")
    public Result add(@RequestBody JobPosition jobPosition) {
        try {
            return this.jobPositionService.add(jobPosition);
        }
        catch(Exception ex) {
            return new ErrorResult(ex.toString());
        }
    }

    @GetMapping("/get_all_position")
    public DataResult<List<JobPosition>> getAll(){
        try {
            return this.jobPositionService.getAll();
        }
        catch(Exception ex) {
            return new ErrorDataResult<List<JobPosition>>(ex.toString());
        }
    }
}
