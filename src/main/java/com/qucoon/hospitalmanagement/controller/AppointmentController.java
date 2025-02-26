package com.qucoon.hospitalmanagement.controller;


import com.qucoon.hospitalmanagement.Service.AppointmentService;
import com.qucoon.hospitalmanagement.model.entity.Appointment;
import com.qucoon.hospitalmanagement.model.request.AppointmentCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentCreateRequest request) {
        int resp = appointmentService.createAppointment(request);
        if (resp < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment failed to create");
        }
        return ResponseEntity.ok("Appointment created successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int appointmentId) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
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
