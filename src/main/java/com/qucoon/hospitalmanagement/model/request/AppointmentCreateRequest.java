package com.qucoon.hospitalmanagement.model.request;


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
    private int patientId;
    private int doctorId;
    private Timestamp appointmentDate;
    private String reason;
}
