package com.finance.management.rest;

import com.finance.management.entity.City;
import com.finance.management.entity.Employer;
import com.finance.management.service.abstracts.CityService;
import com.finance.management.service.abstracts.EmployerService;
import com.finance.management.util.DataResult;
import com.finance.management.util.ErrorDataResult;
import com.finance.management.util.ErrorResult;
import com.finance.management.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer")
public class EmployerController {
    private EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping("/add_employer")
    public Result add(@RequestBody Employer employer) {
        try {
            return this.employerService.add(employer);
        }
        catch(Exception ex) {
            return new ErrorResult(ex.toString());
        }
    }

    @GetMapping("/get_all_employer")
    public DataResult<List<Employer>> getAll(){
        try {
            return this.employerService.getAll();
        }
        catch(Exception ex) {
            return new ErrorDataResult<List<Employer>>(ex.toString());
        }
    }
}
