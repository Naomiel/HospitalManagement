package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.model.entity.MedicationSales;
import com.qucoon.hospitalmanagement.model.entity.ViewMedicationSales;
import com.qucoon.hospitalmanagement.model.response.*;
import com.qucoon.hospitalmanagement.service.MedicationSalesService;
import com.qucoon.hospitalmanagement.model.request.MedicationSalesCreateRequest;
import com.qucoon.hospitalmanagement.model.request.MedicationSalesRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicationSales")
public class MedicationSalesController {
    private final MedicationSalesService medicationSalesService;

    @Autowired
    public MedicationSalesController(MedicationSalesService medicationSalesService) {
        this.medicationSalesService = medicationSalesService;
    }

    @PostMapping("/create")
    public ResponseEntity<AppResponse<Void>> createMedicationSales(@Valid @RequestBody MedicationSalesCreateRequest request){
        return ResponseEntity.ok(medicationSalesService.createMedicationSales(request));
    }

    @PatchMapping("/update/{medicationSalesId}")
    public ResponseEntity<AppResponse<Void>> updateMedicationSales(@Valid @RequestBody MedicationSalesRequest request, @PathVariable String medicationSalesId){
        return ResponseEntity.ok(medicationSalesService.updateMedicationSales(medicationSalesId,request));

    }

    @DeleteMapping("/delete/{medicationSalesId}")
    public ResponseEntity<AppResponse<Void>> deleteMedicationSales(@PathVariable int medicationSalesId){
        return ResponseEntity.ok(medicationSalesService.deleteMedicationSales(medicationSalesId));
    }

    @GetMapping("/all-general")
    public ResponseEntity<AppResponse<List<MedicationSales>>> getMedicationSales(){
        return ResponseEntity.ok(medicationSalesService.getAllMedicationSales());
    }

    @GetMapping("/all")
    public ResponseEntity<AppResponse<List<ViewMedicationSales>>> getActiveMedicationSales(){
        return ResponseEntity.ok(medicationSalesService.getAllActiveMedicationSales());
    }

    @GetMapping("/get/{medicationSalesId}")
    public ResponseEntity<AppResponse<MedicationSalesResponse>> getMedicationSalesById(@PathVariable int medicationSalesId){
        return ResponseEntity.ok(medicationSalesService.getMedicationSalesById(medicationSalesId));
    }
}
