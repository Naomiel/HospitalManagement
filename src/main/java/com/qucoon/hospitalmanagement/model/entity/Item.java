package com.qucoon.hospitalmanagement.model.entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private int itemId;
    private int itemMedicationSalesId;
    private int itemMedicationId;
    private int itemQuantity;
    private String itemStatus;
    private String itemCreatedAt;
    private String itemUpdatedAt;
}
