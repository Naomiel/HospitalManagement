package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.Service.MedicationService;
import com.qucoon.hospitalmanagement.model.request.MedicationAddRequest;
import com.qucoon.hospitalmanagement.model.response.GenericResponse;
import com.qucoon.hospitalmanagement.model.response.GetAllMedicationsResponse;
import com.qucoon.hospitalmanagement.model.response.GetMedicationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medication")
public class MedicationController {
    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping("/create-medication")
    public ResponseEntity<GenericResponse> createMedication(@Valid @RequestBody MedicationAddRequest request){
        return ResponseEntity.ok(medicationService.createMedication(request));
    }

    @PatchMapping("/update-medication/{medicationId}")
    public ResponseEntity<GenericResponse> updateMedication(@Valid @RequestBody MedicationAddRequest request, @PathVariable String medicationId){
        return ResponseEntity.ok(medicationService.updateMedication(medicationId,request));
    }

    @DeleteMapping("/delete-medication/{medicationId}")
    public ResponseEntity<GenericResponse> deleteMedication(@PathVariable int medicationId){
        return ResponseEntity.ok(medicationService.deleteMedication(medicationId));
    }

    @GetMapping("/all-general")
    public ResponseEntity<GetAllMedicationsResponse> getMedications(){
        return ResponseEntity.ok(medicationService.getAllMedication());
    }

    @GetMapping("/all")
    public ResponseEntity<GetAllMedicationsResponse> getActiveMedications(){
        return ResponseEntity.ok(medicationService.getAllActiveMedication());
    }

    @GetMapping("/get-by-id/{medicationId}")
    public ResponseEntity<GetMedicationResponse> getMedicationById(@PathVariable int medicationId) {
        return ResponseEntity.ok(medicationService.getMedicationById(medicationId));
    }
}
