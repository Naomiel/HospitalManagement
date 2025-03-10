package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.service.MedicationSalesService;
import com.qucoon.hospitalmanagement.model.request.MedicationSalesCreateRequest;
import com.qucoon.hospitalmanagement.model.request.MedicationSalesRequest;
import com.qucoon.hospitalmanagement.model.response.GenericResponse;
import com.qucoon.hospitalmanagement.model.response.GetAllMedicationSalesResponse;
import com.qucoon.hospitalmanagement.model.response.GetAllMedicationSalesViewResponse;
import com.qucoon.hospitalmanagement.model.response.GetMedicationSalesResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/medicationSales")
public class MedicationSalesController {
    private final MedicationSalesService medicationSalesService;

    @Autowired
    public MedicationSalesController(MedicationSalesService medicationSalesService) {
        this.medicationSalesService = medicationSalesService;
    }

    @PostMapping("/create-medicationSales")
    public ResponseEntity<GenericResponse> createMedicationSales(@Valid @RequestBody MedicationSalesCreateRequest request){
        return ResponseEntity.ok(medicationSalesService.createMedicationSales(request));
    }

    @PatchMapping("/update/{medicationSalesId}")
    public ResponseEntity<GenericResponse> updateMedicationSales(@Valid @RequestBody MedicationSalesRequest request, @PathVariable String medicationSalesId){
        return ResponseEntity.ok(medicationSalesService.updateMedicationSales(medicationSalesId,request));

    }

    @DeleteMapping("/delete/{medicationSalesId}")
    public ResponseEntity<GenericResponse> deleteMedicationSales(@PathVariable int medicationSalesId){
        return ResponseEntity.ok(medicationSalesService.deleteMedicationSales(medicationSalesId));
    }

    @GetMapping("/all-general")
    public ResponseEntity<GetAllMedicationSalesResponse> getMedicationSales(){
        return ResponseEntity.ok(medicationSalesService.getAllMedicationSales());
    }

    @GetMapping("/all")
    public ResponseEntity<GetAllMedicationSalesViewResponse> getActiveMedicationSales(){
        return ResponseEntity.ok(medicationSalesService.getAllActiveMedicationSales());
    }

    @GetMapping("/get/{medicationSalesId}")
    public ResponseEntity<GetMedicationSalesResponse> getMedicationSalesById(@PathVariable int medicationSalesId){
        return ResponseEntity.ok(medicationSalesService.getMedicationSalesById(medicationSalesId));
    }
}
