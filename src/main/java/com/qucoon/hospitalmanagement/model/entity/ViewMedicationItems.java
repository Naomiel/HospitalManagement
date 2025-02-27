package com.qucoon.hospitalmanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewMedicationItems {
    private int itemId;
    private String medicationName;
    private BigDecimal medicationPrice;
    private int itemQuantity;
}
