package com.qucoon.hospitalmanagement.model.entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Medication {
    private int medicationId;
    private String medicationName;
    private String medicationDescription;
    private int medicationQuantityInStock;
    private double medicationPrice;
    private String medicationExpiryDate;
    private String medicationManufacturer;
    private String medicationStatus;
    private String medicationCreatedAt;
    private String medicationUpdatedAt;
}