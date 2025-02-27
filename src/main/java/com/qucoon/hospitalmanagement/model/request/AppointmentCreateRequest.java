package com.qucoon.hospitalmanagement.model.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreateRequest {
    @NotNull(message = "Appointment PatientId is required")
    @Min(value = 1, message = "Patient ID must be a positive number")
    private int appointmentPatientId;

    @NotNull(message = "Appointment StaffId is required")
    @Min(value = 1, message = "Staff ID must be a positive number")
    private Integer appointmentStaffId;

    @NotNull(message = "Appointment Date is required")
    private Timestamp appointmentDate;
}
