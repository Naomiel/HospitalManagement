package com.qucoon.hospitalmanagement.model.request;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationAddRequest {
    private String medicationName;
    private String medicationDescription;
    private String medicationQuantityInStock;
    private String medicationPrice;
    private  String medicationExpiryDate;
    private String medicationManufacturer;
}
