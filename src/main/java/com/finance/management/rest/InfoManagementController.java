package com.finance.management.rest;

import com.finance.management.service.ParticipantService;
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
    public ResponseEntity<String> updateFullName(@RequestHeader("Authorization") String authToken, @RequestBody UpdateInfoManagementRequest updateInfoManagementRequest) {
        participantService.updateFullName(authToken, updateInfoManagementRequest.getNewFullName());
        return ResponseEntity.ok("Full name successfully updated.");
    }

    @PostMapping("/update-credit-card-info")
    public ResponseEntity<String> updateCreditCardInfo(@RequestHeader("Authorization") String authToken, @RequestBody UpdateInfoManagementRequest updateInfoManagementRequest) {
        participantService.updateCreditCardInfo(authToken, updateInfoManagementRequest.getNewCreditCardInfo());
        return ResponseEntity.ok("Credit card information successfully updated.");
    }

    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestHeader("Authorization") String authToken, @RequestBody UpdateInfoManagementRequest updateInfoManagementRequest) {
        participantService.updatePassword(authToken, updateInfoManagementRequest.getNewPassword());
        return ResponseEntity.ok("Password successfully updated.");
    }
}
