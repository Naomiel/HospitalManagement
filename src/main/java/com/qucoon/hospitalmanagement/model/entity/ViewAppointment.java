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
public class ViewAppointment {
    private String patientFirstName;
    private String patientLastName;
    private int patientAge;
    private String staffFirstName;
    private String staffLastName;
    private int appointmentId;  // Changed from appointmentId to id for consistency with DB
    private int appointmentPatientId;
    private int appointmentStaffId;
    private Timestamp appointmentDate;
    private String appointmentStatus;
    private Timestamp appointmentCreatedAt;
    private Timestamp appointmentUpdatedAt;
}
