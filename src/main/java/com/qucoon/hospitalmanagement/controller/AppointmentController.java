package com.qucoon.hospitalmanagement.controller;


import com.qucoon.hospitalmanagement.Service.AppointmentService;
import com.qucoon.hospitalmanagement.model.entity.Appointment;
import com.qucoon.hospitalmanagement.model.entity.ViewAppointment;
import com.qucoon.hospitalmanagement.model.request.AppointmentCreateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createAppointment(@Valid @RequestBody AppointmentCreateRequest request) {
        int resp = appointmentService.createAppointment(request);
        Map<String, Object> response = new HashMap<>();

        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Appointment creation failed");
            response.put("data", Map.of("appointment", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Appointment created successfully");
        response.put("data", Map.of("appointment", request));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ViewAppointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<ViewAppointment> getAppointmentById(@PathVariable int appointmentId) {
        ViewAppointment appointment = appointmentService.getAppointmentById(appointmentId);
        if (appointment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/update/{appointmentId}")
    public ResponseEntity<String> updateAppointment(@PathVariable int appointmentId, @RequestBody AppointmentCreateRequest request) {
        int result = appointmentService.updateAppointment(appointmentId, request);
        if (result > 0) {
            return ResponseEntity.ok("Appointment updated successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update appointment");
    }

    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int appointmentId) {
        int result = appointmentService.deleteAppointment(appointmentId);
        if (result > 0) {
            return ResponseEntity.ok("Appointment deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete appointment");
    }

    @PutMapping("/reschedule/{appointmentId}")
    public ResponseEntity<String> rescheduleAppointment(@PathVariable int appointmentId, @RequestBody Appointment appointment) {
        int result = appointmentService.rescheduleAppointment(appointmentId, appointment);
        if (result > 0) {
            return ResponseEntity.ok("Appointment rescheduled successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to reschedule appointment");
    }

    @PutMapping("/cancel/{appointmentId}")
    public ResponseEntity<String> cancelAppointment(@PathVariable int appointmentId) {
        int result = appointmentService.cancelAppointment(appointmentId);
        if (result > 0) {
            return ResponseEntity.ok("Appointment canceled successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to cancel appointment");
    }
}
