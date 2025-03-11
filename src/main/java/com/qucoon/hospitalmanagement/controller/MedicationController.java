package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.model.entity.Medication;
import com.qucoon.hospitalmanagement.model.response.*;
import com.qucoon.hospitalmanagement.service.MedicationService;
import com.qucoon.hospitalmanagement.model.request.MedicationAddRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/v1/medication")
public class MedicationController {
    private final MedicationService medicationService;
    private static final DateTimeFormatter MYSQL_TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<AppResponse<Void>> createMedication(@Valid @RequestBody MedicationAddRequest request){
        return ResponseEntity.ok(medicationService.createMedication(request));
    }

    @PatchMapping("/update/{medicationId}")
    public ResponseEntity<AppResponse<Void>> updateMedication(@Valid @RequestBody MedicationAddRequest request, @PathVariable String medicationId){
        return ResponseEntity.ok(medicationService.updateMedication(medicationId,request));
    }

    @DeleteMapping("/delete/{medicationId}")
    public ResponseEntity<AppResponse<Void>> deleteMedication(@PathVariable int medicationId){
        return ResponseEntity.ok(medicationService.deleteMedication(medicationId));
    }

    @GetMapping("/all-general")
    public ResponseEntity<AppResponse<List<Medication>>> getMedications(){
        return ResponseEntity.ok(medicationService.getAllMedication());
    }

    @GetMapping("/all")
    public ResponseEntity<AppResponse<List<Medication>>> getActiveMedications(){
        return ResponseEntity.ok(medicationService.getAllActiveMedication());
    }

    @GetMapping("/get/{medicationId}")
    public ResponseEntity<AppResponse<Medication>> getMedicationById(@PathVariable int medicationId) {
        return ResponseEntity.ok(medicationService.getMedicationById(medicationId));
    }

    @GetMapping("/get-medication-revenue/{medicationId}")
    public ResponseEntity<AppResponse<MedicationRevenueResp>> getMedicationRevenue(@PathVariable int medicationId,
                                                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
                                                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate) {
        String startDateStr = startDate.format(MYSQL_TIMESTAMP_FORMAT);
        String endDateStr = endDate.format(MYSQL_TIMESTAMP_FORMAT);
        return ResponseEntity.ok(medicationService.getMedicationRevenueByIdAndDate(medicationId, startDateStr, endDateStr));
    }
}
