package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.Service.PatientService;
import com.qucoon.hospitalmanagement.model.entity.Patient;
import com.qucoon.hospitalmanagement.model.request.PatientCreateRequest;
import com.qucoon.hospitalmanagement.model.response.PatientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientCreateRequest request){
        var resp =patientService.createPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getPatients(){
        return ResponseEntity.ok(patientService.getAllPatient());
    }


    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int patientId){
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<String> updatePatient(@PathVariable int patientId, @RequestBody Patient patient) {
        int result = patientService.updatePatient(patientId, patient);
        if (result < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update patient");
        }
        return ResponseEntity.ok("Patient updated successfully");
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> deletePatient(@PathVariable int patientId) {
        int result = patientService.deletePatient(patientId);
        if (result < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete patient");
        }
        return ResponseEntity.ok("Patient deleted successfully");
    }

}
