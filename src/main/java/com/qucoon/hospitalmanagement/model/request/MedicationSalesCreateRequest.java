package com.qucoon.hospitalmanagement.model.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MedicationSalesCreateRequest {
    @NotNull(message = "medicationSalesPatientId is required")
    private int medicationSalesPatientId;

    @NotNull(message = "medicationSalesStaffId is required")
    private int medicationSalesStaffId;
    
    @NotBlank(message = "list of medicationId is required")
    private List<MedicationList> medicationIdAndQuantity;
}
