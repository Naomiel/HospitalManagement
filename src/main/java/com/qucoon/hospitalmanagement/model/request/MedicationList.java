package com.qucoon.hospitalmanagement.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationList {
    private Integer itemMedicationId;
    private Integer itemQuantity;
}
