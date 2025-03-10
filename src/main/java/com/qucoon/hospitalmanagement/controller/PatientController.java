package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.service.PatientService;
import com.qucoon.hospitalmanagement.model.request.PatientCreateRequest;
import com.qucoon.hospitalmanagement.model.response.AppResponse;
import com.qucoon.hospitalmanagement.model.response.PatientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;


    @PostMapping("/create-patient")
    public ResponseEntity<AppResponse<PatientResponse>> createPatient(@RequestBody PatientCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(request));
    }

    @GetMapping("/all")
    public ResponseEntity<AppResponse<List<PatientResponse>>> getAllPatient() {
        return ResponseEntity.ok(patientService.getAllPatient());
    }


    @GetMapping("/{patientId}")
    public ResponseEntity<AppResponse<PatientResponse>> getPatientById(@PathVariable int patientId) {
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }


    @PatchMapping("/update/{patientId}")
    public ResponseEntity<AppResponse<PatientResponse>> updatePatient(@PathVariable int patientId, @RequestBody PatientCreateRequest request) {
        return ResponseEntity.ok(patientService.updatePatient(patientId, request));
    }

    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<AppResponse<Void>> deletePatient(@PathVariable int patientId) {
        AppResponse<Void> resp = patientService.deletePatient(patientId);

        if ("00".equals(resp.getResponseCode())) {
            return ResponseEntity.ok(resp);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }

    }
}


