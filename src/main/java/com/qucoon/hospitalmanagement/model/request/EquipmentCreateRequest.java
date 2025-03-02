package com.qucoon.hospitalmanagement.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EquipmentCreateRequest {

    @NotBlank(message = "equipmentName is required")
    private String equipmentName;

    @NotBlank(message = "equipmentCategory is required")
    private String equipmentCategory;

    @NotNull(message = "equipmentQuantity is required")
    @Positive(message = "equipmentQuantity must be greater than zero")
    private Integer equipmentQuantity;

    @NotBlank(message = "equipmentPurchaseDate is required")
    private String equipmentPurchaseDate;
}

