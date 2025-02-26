package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.Service.MedicationService;
import com.qucoon.hospitalmanagement.model.entity.Medication;
import com.qucoon.hospitalmanagement.model.request.MedicationAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medication")
public class MedicationController {
    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping("/create-medication")
    public ResponseEntity<String> createMedication(@RequestBody MedicationAddRequest request){
        var resp =medicationService.createMedication(request);
        if(resp<1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Medication failed to create");
        return ResponseEntity.ok("Medication created successfully");
    }

    @PostMapping("/update-medication/{medicationId}")
    public ResponseEntity<String> updateMedication(@RequestBody MedicationAddRequest request, @PathVariable String medicationId){
        var resp =medicationService.updateMedication(medicationId,request);
        if(resp<1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Medication failed to update");
        return ResponseEntity.ok("Medication updated successfully");
    }

    @PostMapping("/delete-medication/{medicationId}")
    public ResponseEntity<String> deleteMedication(@PathVariable int medicationId){
        var resp = medicationService.deleteMedication(medicationId);
        if(resp<1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Medication failed to delete");
        return ResponseEntity.ok("Medication deleted successfully");
    }

    @GetMapping("/all-general")
    public ResponseEntity<List<Medication>> getMedications(){
        return ResponseEntity.ok(medicationService.getAllMedication());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Medication>> getActiveMedications(){
        return ResponseEntity.ok(medicationService.getAllActiveMedication());
    }

    @GetMapping("/get-by-id/{medicationId}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable int medicationId){
        return ResponseEntity.ok(medicationService.getMedicationById(medicationId));
    }
}
