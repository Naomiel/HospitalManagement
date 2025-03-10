package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.service.EquipmentService;
import com.qucoon.hospitalmanagement.model.request.EquipmentCreateRequest;
import com.qucoon.hospitalmanagement.model.request.EquipmentUpdateRequest;
import com.qucoon.hospitalmanagement.model.response.EquipmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<EquipmentResponse> createEquipment(@Valid @RequestBody EquipmentCreateRequest request) {
        var response = equipmentService.createEquipment(request);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/view")
    public ResponseEntity<EquipmentResponse> getAllEquipment() {
        var response = equipmentService.getAllEquipment();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/view/{equipmentId}")
    public ResponseEntity<EquipmentResponse> getEquipmentById(@PathVariable int equipmentId) {

        var response = equipmentService.getEquipmentById(equipmentId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{equipmentId}")
    public ResponseEntity<EquipmentResponse> updateEquipment(@Valid @RequestBody EquipmentUpdateRequest request, @PathVariable int equipmentId) {
        var response = equipmentService.updateEquipment(request, equipmentId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{equipmentId}")
    public ResponseEntity<EquipmentResponse>  deleteEquipment(@PathVariable int equipmentId) {
        var response = equipmentService.deleteEquipment(equipmentId);
        return ResponseEntity.ok(response);

    }
}
