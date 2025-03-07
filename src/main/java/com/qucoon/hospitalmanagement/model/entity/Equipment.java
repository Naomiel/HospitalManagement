package com.qucoon.hospitalmanagement.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Equipment {
    private int equipmentId;
    private String equipmentName;
    private String equipmentCategory;
    private Integer equipmentQuantity;
    private String equipmentPurchaseDate;
    private String equipmentStatus;
    private String equipmentCreatedAt;
    private String equipmentUpdatedAt;
}
