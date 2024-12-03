package com.finance.management.rest;

import com.finance.management.entity.City;
import com.finance.management.entity.SystemEmployee;
import com.finance.management.service.abstracts.CityService;
import com.finance.management.service.abstracts.SystemEmployeeService;
import com.finance.management.util.DataResult;
import com.finance.management.util.ErrorDataResult;
import com.finance.management.util.ErrorResult;
import com.finance.management.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system_employee")
public class SystemEmployeeController {
    private SystemEmployeeService systemEmployeeService;

    @Autowired
    public SystemEmployeeController(SystemEmployeeService systemEmployeeService) {
        this.systemEmployeeService = systemEmployeeService;
    }

    @PostMapping("/add_system")
    public Result add(@RequestBody SystemEmployee systemEmployee) {
        try {
            return this.systemEmployeeService.add(systemEmployee);
        }
        catch(Exception ex) {
            return new ErrorResult(ex.toString());
        }
    }

    @GetMapping("/get_all_systems")
    public DataResult<List<SystemEmployee>> getAll(){
        try {
            return this.systemEmployeeService.getAll();
        }
        catch(Exception ex) {
            return new ErrorDataResult<List<SystemEmployee>>(ex.toString());
        }
    }
}
