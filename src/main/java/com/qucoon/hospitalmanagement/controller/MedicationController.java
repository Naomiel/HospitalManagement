package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.Service.MedicationService;
import com.qucoon.hospitalmanagement.model.entity.Medication;
import com.qucoon.hospitalmanagement.model.request.MedicationAddRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/medication")
public class MedicationController {
    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping("/create-medication")
    public ResponseEntity<Map<String, Object>> createMedication(@Valid @RequestBody MedicationAddRequest request){
        var resp =medicationService.createMedication(request);
        Map<String, Object> response = new HashMap<>();

        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Medication creation failed");
            response.put("data", Map.of("medication", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Medication created successfully");
        response.put("data", Map.of("medication", request));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update-medication/{medicationId}")
    public ResponseEntity<Map<String, Object>> updateMedication(@Valid @RequestBody MedicationAddRequest request, @PathVariable String medicationId){
        var resp =medicationService.updateMedication(medicationId,request);
        Map<String, Object> response = new HashMap<>();

        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Unable to update medication");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Medication updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-medication/{medicationId}")
    public ResponseEntity<Map<String, Object>> deleteMedication(@PathVariable int medicationId){
        var resp = medicationService.deleteMedication(medicationId);
        Map<String, Object> response = new HashMap<>();

        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Delete medication failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Medication deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-general")
    public ResponseEntity<Map<String, Object>> getMedications(){
        var resp = medicationService.getAllMedication();
        Map<String, Object> response = new HashMap<>();

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Medication created successfully");
        response.put("data", Map.of("medication", resp));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getActiveMedications(){
        var resp = medicationService.getAllActiveMedication();
        Map<String, Object> response = new HashMap<>();

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Medication created successfully");
        response.put("data", Map.of("medication", resp));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-by-id/{medicationId}")
    public ResponseEntity<Map<String, Object>> getMedicationById(@PathVariable int medicationId){
        var resp = medicationService.getMedicationById(medicationId);
        Map<String, Object> response = new HashMap<>();

        if (resp == null) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Medication fetch failed");
            response.put("data", Map.of("medication", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Medication fetch successfully");
        response.put("data", Map.of("medication", resp));
        return ResponseEntity.ok(response);
    }
}
