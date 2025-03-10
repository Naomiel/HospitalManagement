package com.qucoon.hospitalmanagement.controller;


import com.qucoon.hospitalmanagement.model.entity.ViewAppointment;
import com.qucoon.hospitalmanagement.model.response.AppResponse;
import com.qucoon.hospitalmanagement.model.response.AppointmentResponse;
import com.qucoon.hospitalmanagement.service.AppointmentService;
import com.qucoon.hospitalmanagement.model.entity.Appointment;
import com.qucoon.hospitalmanagement.model.request.AppointmentCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;



    @PostMapping("/create")
    public ResponseEntity<AppResponse<AppointmentResponse>> createAppointment(@Valid @RequestBody AppointmentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(request));

    }

    @GetMapping("/all")
    public ResponseEntity<AppResponse<List<ViewAppointment>>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }


    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppResponse<ViewAppointment>> getAppointmentById(@PathVariable int appointmentId) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(appointmentId));
    }

    @PatchMapping("/update/{appointmentId}")
    public ResponseEntity<AppResponse<AppointmentResponse>> updateAppointment(  @PathVariable int appointmentId, @Valid @RequestBody AppointmentCreateRequest request) {
        return ResponseEntity.ok(appointmentService.updateAppointment(appointmentId, request));
    }

    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<AppResponse<Void>> deleteAppointment(@PathVariable int appointmentId) {
        return ResponseEntity.ok(appointmentService.deleteAppointment(appointmentId));
    }

    @PatchMapping("/reschedule/{appointmentId}")
    public ResponseEntity<AppResponse<AppointmentResponse>> rescheduleAppointment(  @PathVariable int appointmentId, @Valid @RequestBody AppointmentCreateRequest request) {
        return ResponseEntity.ok(appointmentService.rescheduleAppointment(appointmentId,request));
    }


    @DeleteMapping("/cancel/{appointmentId}")
    public ResponseEntity<AppResponse<Void>> cancelAppointment(@PathVariable int appointmentId) {
        return ResponseEntity.ok(appointmentService.cancelAppointment(appointmentId));
    }

}
