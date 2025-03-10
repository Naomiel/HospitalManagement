package com.qucoon.hospitalmanagement.model.request;


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
    private int appointmentPatientId;

    private Integer appointmentStaffId;

    @NotNull(message = "Appointment Date is required")
    private Timestamp appointmentDate;
    private String appointmentStatus;
}
