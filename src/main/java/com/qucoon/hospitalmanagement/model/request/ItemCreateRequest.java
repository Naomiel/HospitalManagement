package com.qucoon.hospitalmanagement.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemCreateRequest {
    private String itemMedicationSalesId;
    private String itemMedicationId;
    private int itemQuantity;
}
