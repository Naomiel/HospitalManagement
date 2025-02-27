package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.Service.MedicationSalesService;
import com.qucoon.hospitalmanagement.model.entity.MedicationSales;
import com.qucoon.hospitalmanagement.model.request.MedicationSalesCreateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/medicationSales")
public class MedicationSalesController {
    private final MedicationSalesService medicationSalesService;

    @Autowired
    public MedicationSalesController(MedicationSalesService medicationSalesService) {
        this.medicationSalesService = medicationSalesService;
    }

    @PostMapping("/create-medicationSales")
    public ResponseEntity<Map<String, Object>> createMedicationSales(@RequestBody MedicationSalesCreateRequest request){
        var resp =medicationSalesService.createMedicationSales(request);
        Map<String, Object> response = new HashMap<>();

        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Create medicationSales failed");
            response.put("data", Map.of("medication", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "MedicationSales created successfully");
        response.put("data", Map.of("medication", "{}"));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update-medicationSales/{medicationSalesId}")
    public ResponseEntity<Map<String, Object>> updateMedicationSales(@RequestBody MedicationSalesCreateRequest request, @PathVariable String medicationSalesId){
        var resp =medicationSalesService.updateMedicationSales(medicationSalesId,request);
        Map<String, Object> response = new HashMap<>();

        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Update medicationSales failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "MedicationSales updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-medicationSales/{medicationSalesId}")
    public ResponseEntity<Map<String, Object>> deleteMedicationSales(@PathVariable int medicationSalesId){
        var resp = medicationSalesService.deleteMedicationSales(medicationSalesId);
        Map<String, Object> response = new HashMap<>();

        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Delete medicationSales failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "MedicationSales deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-general")
    public ResponseEntity<Map<String, Object>> getMedicationSales(){
        var resp = medicationSalesService.getAllMedicationSales();
        Map<String, Object> response = new HashMap<>();

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "All present MedicationSales fetched successfully");
        response.put("data", Map.of("medication", resp));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getActiveMedicationSales(){
        var resp = medicationSalesService.getAllActiveMedicationSales();
        Map<String, Object> response = new HashMap<>();

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "MedicationSales fetched successfully");
        response.put("data", Map.of("medication", resp));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-by-id/{medicationSalesId}")
    public ResponseEntity<Map<String, Object>> getMedicationSalesById(@PathVariable int medicationSalesId){
        var resp = medicationSalesService.getMedicationSalesById(medicationSalesId);
        Map<String, Object> response = new HashMap<>();

        if (resp == null) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Fetch MedicationSales failed");
            response.put("data", Map.of("medication", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "MedicationSales fetched successfully");
        response.put("data", Map.of("medication", resp));
        return ResponseEntity.ok(response);
    }
}
