package com.qucoon.hospitalmanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private int appointmentId;  // Changed from appointmentId to id for consistency with DB
    private int appointmentPatientId;
    private int appointmentStaffId;
    private Timestamp appointmentDate;
    private String appointmentStatus;
    private Timestamp appointmentCreatedAt;
    private Timestamp appointmentUpdatedAt;
}

