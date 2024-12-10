package com.finance.management.rest;

import com.finance.management.entity.ServiceMenu;
import com.finance.management.service.ServiceMenuService;
import com.finance.management.util.AddServiceMenuRequest;
import com.finance.management.util.Response;
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
    public ResponseEntity<?> getAllServiceMenus() {
        Response response = new Response();
        try {
            List<ServiceMenu> serviceMenuList = serviceMenuService.getAllServiceMenus();
            return ResponseEntity.ok(serviceMenuList);
        } catch (Exception e) {
            response.setMessage("Failed get service menu: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    // Only for injecting data to keep it organized
    @PostMapping("/add")
    public ResponseEntity<?> addServiceMenu(@RequestBody AddServiceMenuRequest addServiceMenuRequest) {
        Response response = new Response();
        try {
            ServiceMenu serviceMenu = serviceMenuService.addServiceMenu(addServiceMenuRequest);
            return ResponseEntity.ok(serviceMenu);
        } catch (Exception e) {
            response.setMessage("Failed add service menu: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }
}
