package com.finance.management.rest;

import com.finance.management.entity.City;
import com.finance.management.entity.JobSeeker;
import com.finance.management.service.abstracts.CityService;
import com.finance.management.service.abstracts.JobSeekerService;
import com.finance.management.util.DataResult;
import com.finance.management.util.ErrorDataResult;
import com.finance.management.util.ErrorResult;
import com.finance.management.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job_seeker")
public class JobSeekerController {
    private JobSeekerService jobSeekerService;

    @Autowired
    public JobSeekerController(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }

    @PostMapping("/add_seeker")
    public Result add(@RequestBody JobSeeker jobSeeker) {
        try {
            return this.jobSeekerService.add(jobSeeker);
        }
        catch(Exception ex) {
            return new ErrorResult(ex.toString());
        }
    }

    @GetMapping("/get_all_seeker")
    public DataResult<List<JobSeeker>> getAll(){
        try {
            return this.jobSeekerService.getAll();
        }
        catch(Exception ex) {
            return new ErrorDataResult<List<JobSeeker>>(ex.toString());
        }
    }
}
