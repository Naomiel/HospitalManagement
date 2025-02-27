package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.Service.MedicationSalesService;
import com.qucoon.hospitalmanagement.model.entity.MedicationSales;
import com.qucoon.hospitalmanagement.model.request.MedicationSalesCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicationSales")
public class MedicationSalesController {
    private final MedicationSalesService medicationSalesService;

    @Autowired
    public MedicationSalesController(MedicationSalesService medicationSalesService) {
        this.medicationSalesService = medicationSalesService;
    }

    @PostMapping("/create-medicationSales")
    public ResponseEntity<String> createMedicationSales(@RequestBody MedicationSalesCreateRequest request){
        var resp =medicationSalesService.createMedicationSales(request);
        if(resp<1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MedicationSales failed to create");
        return ResponseEntity.ok("MedicationSales created successfully");
    }

    @PostMapping("/update-medicationSales/{medicationSalesId}")
    public ResponseEntity<String> updateMedicationSales(@RequestBody MedicationSalesCreateRequest request, @PathVariable String medicationSalesId){
        var resp =medicationSalesService.updateMedicationSales(medicationSalesId,request);
        if(resp<1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MedicationSales failed to update");
        return ResponseEntity.ok("MedicationSales updated successfully");
    }

    @PostMapping("/delete-medicationSales/{medicationSalesId}")
    public ResponseEntity<String> deleteMedicationSales(@PathVariable int medicationSalesId){
        var resp = medicationSalesService.deleteMedicationSales(medicationSalesId);
        if(resp<1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MedicationSales failed to delete");
        return ResponseEntity.ok("MedicationSales deleted successfully");
    }

    @GetMapping("/all-general")
    public ResponseEntity<List<MedicationSales>> getMedicationSaless(){
        return ResponseEntity.ok(medicationSalesService.getAllMedicationSales());
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicationSales>> getActiveMedicationSaless(){
        return ResponseEntity.ok(medicationSalesService.getAllActiveMedicationSales());
    }

    @GetMapping("/get-by-id/{medicationSalesId}")
    public ResponseEntity<MedicationSales> getMedicationSalesById(@PathVariable int medicationSalesId){
        return ResponseEntity.ok(medicationSalesService.getMedicationSalesById(medicationSalesId));
    }
}
