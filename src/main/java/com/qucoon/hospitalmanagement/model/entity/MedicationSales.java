package com.qucoon.hospitalmanagement.model.entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationSales {
    private int medicationSalesId;
    private int medicationSalesPatientId;
    private int medicationSalesStaffId;
    private String medicationSalesStatus;
    private String medicationSalesCreatedAt;
    private String medicationSalesUpdatedAt;
}
