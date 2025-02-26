package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.Service.StaffService;
import com.qucoon.hospitalmanagement.model.entity.Staff;
import com.qucoon.hospitalmanagement.model.request.StaffCreateRequest;
import com.qucoon.hospitalmanagement.model.request.StaffUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createStaff(@Valid @RequestBody StaffCreateRequest request) {
        var resp = staffService.createStaff(request);

        Map<String, Object> response = new HashMap<>();

        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Staff creation failed");
            response.put("data", Map.of("staff", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Staff created successfully");
        response.put("data", Map.of("staff", request));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/view")
    public ResponseEntity<Map<String, Object>> getAllStaff() {
        Map<String, Object> response = new HashMap<>();
        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Staff fetched successfully");
        response.put("data", Map.of("staff", staffService.getAllStaff()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/view/{staffId}")
    public ResponseEntity<Map<String, Object>> getStaffById(@PathVariable int staffId) {

        var resp = staffService.getStaffById(staffId);

        Map<String, Object> response = new HashMap<>();
        if (resp == null) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Unable to find staff");
            response.put("data", Map.of("staff", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Staff fetch successfully");
        response.put("data", Map.of("staff", resp));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{staffId}")
    public ResponseEntity<Map<String, Object>> updateStaff(@Valid @RequestBody StaffUpdateRequest request, @PathVariable int staffId) {
        var resp = staffService.updateStaff(request, staffId);

        Map<String, Object> response = new HashMap<>();
        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Unable to update staff");
//            response.put("data", Map.of("staff", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Staff updated successfully");
//        response.put("data", Map.of("staff", request));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{staffId}")
    public ResponseEntity<Map<String, Object>> deleteStaff(@PathVariable int staffId) {
        var resp = staffService.deleteStaff(staffId);

        Map<String, Object> response = new HashMap<>();
        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Unable to delete staff");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Staff deleted successfully");
        return ResponseEntity.ok(response);
    }
}
