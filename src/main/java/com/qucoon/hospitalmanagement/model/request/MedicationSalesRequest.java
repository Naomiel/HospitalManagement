package com.qucoon.hospitalmanagement.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationSalesRequest {
    private int medicationSalesPatientId;
    private int medicationSalesStaffId;

    public MedicationSalesRequest(int medicationSalesPatientId, int medicationSalesStaffId) {
        this.medicationSalesPatientId = medicationSalesPatientId;
        this.medicationSalesStaffId = medicationSalesStaffId;
    }
}
