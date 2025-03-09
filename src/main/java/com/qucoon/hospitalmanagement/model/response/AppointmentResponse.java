package com.qucoon.hospitalmanagement.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private int appointmentId;
    private int appointmentPatientId;
    private int appointmentStaffId;
    private Timestamp appointmentDate;
    private String appointmentStatus;
}