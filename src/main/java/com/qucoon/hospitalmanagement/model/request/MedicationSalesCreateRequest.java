package com.qucoon.hospitalmanagement.model.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "The medication list cannot be empty")
    private List<MedicationList> medicationIdAndQuantity;
}
