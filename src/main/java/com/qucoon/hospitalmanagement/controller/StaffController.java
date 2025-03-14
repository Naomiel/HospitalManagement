package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.service.StaffService;
import com.qucoon.hospitalmanagement.model.request.StaffCreateRequest;
import com.qucoon.hospitalmanagement.model.request.StaffUpdateRequest;
import com.qucoon.hospitalmanagement.model.response.StaffResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/create")
    public ResponseEntity<StaffResponse> createStaff(@Valid @RequestBody StaffCreateRequest request) {
        var response = staffService.createStaff(request);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/all")
    public ResponseEntity<StaffResponse> getAllStaff() {
        var response = staffService.getAllStaff();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{staffId}")
    public ResponseEntity<StaffResponse> getStaffById(@PathVariable int staffId) {

        var response = staffService.getStaffById(staffId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{staffId}")
    public ResponseEntity<StaffResponse> updateStaff(@Valid @RequestBody StaffUpdateRequest request, @PathVariable int staffId) {
        var response = staffService.updateStaff(request, staffId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{staffId}")
    public ResponseEntity<StaffResponse>  deleteStaff(@PathVariable int staffId) {
        var response = staffService.deleteStaff(staffId);
        return ResponseEntity.ok(response);

    }
}
