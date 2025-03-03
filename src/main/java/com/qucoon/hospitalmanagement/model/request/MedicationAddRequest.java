package com.qucoon.hospitalmanagement.model.request;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationAddRequest {
    @NotBlank(message = "medicationName is required")
    private String medicationName;

    @NotBlank(message = "medicationDescription is required")
    private String medicationDescription;

    @NotNull(message = "medicationQuantityInStock is required")
    @Positive(message = "medicationQuantityInStock must be greater than zero")
    private Integer medicationQuantityInStock;

    @NotNull(message = "medicationPrice is required")
    @Positive(message = "medicationPrice must be greater than zero")
    @Digits(integer = 1000000, fraction = 2)
    private double medicationPrice;

    @NotBlank(message = "medicationExpiryDate is required")
    private  String medicationExpiryDate;

    @NotBlank(message = "medicationManufacturer is required")
    private String medicationManufacturer;
}
