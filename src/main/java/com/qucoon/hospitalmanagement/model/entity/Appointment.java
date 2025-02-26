package com.qucoon.hospitalmanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private int id;  // Changed from appointmentId to id for consistency with DB
    private int patientId;
    private int doctorId;
    private Timestamp appointmentDate;
    private String status;
    private String reason;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
