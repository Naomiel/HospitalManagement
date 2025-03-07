package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.Service.PatientService;
import com.qucoon.hospitalmanagement.model.entity.Patient;
import com.qucoon.hospitalmanagement.model.request.PatientCreateRequest;
import com.qucoon.hospitalmanagement.model.request.StaffCreateRequest;
import com.qucoon.hospitalmanagement.model.request.StaffUpdateRequest;
import com.qucoon.hospitalmanagement.model.response.PatientResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

//    @PostMapping
//    public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientCreateRequest request){
//        var resp =patientService.createPatient(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
//    }

    @PostMapping("/create-patient")
    public ResponseEntity<Map<String, Object>> createPatient(@RequestBody PatientCreateRequest request) {
        var resp = patientService.createPatient(request);

        Map<String, Object> response = new HashMap<>();

        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Patient creation failed");
            response.put("data", Map.of("patient", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Staff created successfully");
        response.put("data", Map.of("staff", request));
        return ResponseEntity.ok(response);
    }

//    @GetMapping
//    public ResponseEntity<List<Patient>> getPatients(){
//        return ResponseEntity.ok(patientService.getAllPatient());
//    }


    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllPatient() {
        Map<String, Object> response = new HashMap<>();
        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Patients fetched successfully");
        response.put("data", Map.of("patient", patientService.getAllPatient()));
        return ResponseEntity.ok(response);
    }


//    @GetMapping("/{patientId}")
//    public ResponseEntity<Patient> getPatientById(@PathVariable int patientId){
//        return ResponseEntity.ok(patientService.getPatientById(patientId));
//    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Map<String, Object>> getPatientById(@PathVariable int patientId) {

        var resp = patientService.getPatientById(patientId);

        Map<String, Object> response = new HashMap<>();
        if (resp == null) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Unable to find patient");
            response.put("data", Map.of("patient", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Patient fetch successfully");
        response.put("data", Map.of("patient", resp));
        return ResponseEntity.ok(response);
    }

//    @PutMapping("/{patientId}")
//    public ResponseEntity<String> updatePatient(@PathVariable int patientId, @RequestBody Patient patient) {
//        int result = patientService.updatePatient(patientId, patient);
//        if (result < 1) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update patient");
//        }
//        return ResponseEntity.ok("Patient updated successfully");
//    }

    @PatchMapping("/{patientId}")
    public ResponseEntity<Map<String, Object>> updatePatient(@PathVariable int patientId, @RequestBody Patient patient) {
        var resp = patientService.updatePatient(patientId, patient);

        Map<String, Object> response = new HashMap<>();
        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Unable to update patient");
//            response.put("data", Map.of("staff", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Patient updated successfully");
//        response.put("data", Map.of("staff", request));
        return ResponseEntity.ok(response);
    }

//    @DeleteMapping("/{patientId}")
//    public ResponseEntity<String> deletePatient(@PathVariable int patientId) {
//        int result = patientService.deletePatient(patientId);
//        if (result < 1) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete patient");
//        }
//        return ResponseEntity.ok("Patient deleted successfully");
//    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Map<String, Object>> deletePatient(@PathVariable int patientId) {
        var resp = patientService.deletePatient(patientId);

        Map<String, Object> response = new HashMap<>();
        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Unable to delete patient");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Patient deleted successfully");
        return ResponseEntity.ok(response);
    }
}



