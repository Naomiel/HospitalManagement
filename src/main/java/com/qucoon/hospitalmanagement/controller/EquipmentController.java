package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.Service.EquipmentService;
import com.qucoon.hospitalmanagement.model.entity.Equipment;
import com.qucoon.hospitalmanagement.model.request.EquipmentCreateRequest;
import com.qucoon.hospitalmanagement.model.request.EquipmentUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createEquipment(@Valid @RequestBody EquipmentCreateRequest request) {
        var resp = equipmentService.createEquipment(request);

        Map<String, Object> response = new HashMap<>();

        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Equipment creation failed");
            response.put("data", Map.of("equipment", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Equipment created successfully");
        response.put("data", Map.of("equipment", request));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/view")
    public ResponseEntity<Map<String, Object>> getAllEquipment() {
        Map<String, Object> response = new HashMap<>();
        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Equipment fetched successfully");
        response.put("data", Map.of("equipment", equipmentService.getAllEquipment()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/view/{equipmentId}")
    public ResponseEntity<Map<String, Object>> getEquipmentById(@PathVariable int equipmentId) {

        var resp = equipmentService.getEquipmentById(equipmentId);

        Map<String, Object> response = new HashMap<>();
        if (resp == null) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Unable to find equipment");
            response.put("data", Map.of("equipment", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Equipment fetch successfully");
        response.put("data", Map.of("equipment", resp));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{equipmentId}")
    public ResponseEntity<Map<String, Object>> updateEquipment(@Valid @RequestBody EquipmentUpdateRequest request, @PathVariable int equipmentId) {
        var resp = equipmentService.updateEquipment(request, equipmentId);

        Map<String, Object> response = new HashMap<>();
        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Unable to update equipment");
//            response.put("data", Map.of("equipment", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Equipment updated successfully");
//        response.put("data", Map.of("equipment", request));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{equipmentId}")
    public ResponseEntity<Map<String, Object>> deleteEquipment(@PathVariable int equipmentId) {
        var resp = equipmentService.deleteEquipment(equipmentId);

        Map<String, Object> response = new HashMap<>();
        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Unable to delete equipment");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Equipment deleted successfully");
        return ResponseEntity.ok(response);
    }
}
