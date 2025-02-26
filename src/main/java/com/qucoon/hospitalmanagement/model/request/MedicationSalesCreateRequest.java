package com.qucoon.hospitalmanagement.model.request;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MedicationSalesCreateRequest {
    private String medicationSalesPatientId;
    private int medicationSalesStaffId;
}
