package com.finance.management.rest;

import com.finance.management.entity.ServiceMenu;
import com.finance.management.service.ServiceMenuService;
import com.finance.management.util.AddServiceMenuRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-menu")
public class ServiceMenuController {
    @Autowired
    private ServiceMenuService serviceMenuService;

    @GetMapping("/list")
    public ResponseEntity<List<ServiceMenu>> getAllServiceMenus() {
        List<ServiceMenu> serviceMenuList = serviceMenuService.getAllServiceMenus();
        return ResponseEntity.ok(serviceMenuList);
    }

    // Only for injecting data to keep it organized
    @PostMapping("/add")
    public ResponseEntity<ServiceMenu> addServiceMenu(@RequestBody AddServiceMenuRequest addServiceMenuRequest) {
        ServiceMenu serviceMenu = serviceMenuService.addServiceMenu(addServiceMenuRequest);
        return ResponseEntity.ok(serviceMenu);
    }
}
