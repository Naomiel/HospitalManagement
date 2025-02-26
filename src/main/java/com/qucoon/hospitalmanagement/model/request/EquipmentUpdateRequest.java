package com.qucoon.hospitalmanagement.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EquipmentUpdateRequest {
    private String equipmentName;
    private String equipmentCategory;
    private Integer equipmentQuantity;
    private String equipmentPurchaseDate;
    private String equipmentStatus;
}


