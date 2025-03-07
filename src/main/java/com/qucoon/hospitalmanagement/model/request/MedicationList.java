package com.qucoon.hospitalmanagement.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationList {
    @NotNull(message = "itemMedicationId is required")
    private Integer itemMedicationId;
    @NotNull(message = "itemQuantity is required")
    private Integer itemQuantity;
}
