package com.finance.management.rest;

import com.finance.management.service.ParticipantService;
import com.finance.management.util.Response;
import com.finance.management.util.UpdateInfoManagementRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info-management")
public class InfoManagementController {
    @Autowired
    private ParticipantService participantService;

    @PostMapping("/update-fullname")
    public ResponseEntity<?> updateFullName(@RequestHeader("Authorization") String authToken, @RequestBody UpdateInfoManagementRequest updateInfoManagementRequest) {
        Response response = new Response();
        try {
            participantService.updateFullName(authToken, updateInfoManagementRequest.getNewFullName());
            response.setMessage("Full name successfully updated");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Update full name failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/update-credit-card-info")
    public ResponseEntity<?> updateCreditCardInfo(@RequestHeader("Authorization") String authToken, @RequestBody UpdateInfoManagementRequest updateInfoManagementRequest) {
        Response response = new Response();
        try {
            participantService.updateCreditCardInfo(authToken, updateInfoManagementRequest.getNewCreditCardInfo());
            response.setMessage("Credit card information successfully updated");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Update credit card failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestHeader("Authorization") String authToken, @RequestBody UpdateInfoManagementRequest updateInfoManagementRequest) {
        Response response = new Response();
        try {
            participantService.updatePassword(authToken, updateInfoManagementRequest.getOldPassword(), updateInfoManagementRequest.getNewPassword());
            response.setMessage("Password successfully updated");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Update password failed: " + e.getMessage());
        }
        return ResponseEntity.badRequest().body(response);
    }
}
