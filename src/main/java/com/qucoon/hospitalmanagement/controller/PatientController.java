package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.Service.PatientService;
import com.qucoon.hospitalmanagement.model.entity.Patient;
import com.qucoon.hospitalmanagement.model.request.PatientCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/create-patient")
    public ResponseEntity<String> createPatient(@RequestBody PatientCreateRequest request){
        var resp =patientService.createPatient(request);
        if(resp<1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient failed to create");
        return ResponseEntity.ok("Patient created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getPatients(){
        return ResponseEntity.ok(patientService.getAllPatient());
    }


    @GetMapping("/get-by-id/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int patientId){
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

    @PutMapping("/update/{patientId}")
    public ResponseEntity<String> updatePatient(@PathVariable int patientId, @RequestBody PatientCreateRequest request) {
        int result = patientService.updatePatient(patientId, request);
        if (result < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update patient");
        }
        return ResponseEntity.ok("Patient updated successfully");
    }

    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<String> deletePatient(@PathVariable int patientId) {
        int result = patientService.deletePatient(patientId);
        if (result < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete patient");
        }
        return ResponseEntity.ok("Patient deleted successfully");
    }

}
