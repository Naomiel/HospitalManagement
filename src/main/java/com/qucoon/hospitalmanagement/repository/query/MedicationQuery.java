package com.qucoon.hospitalmanagement.repository.query;

public class MedicationQuery {
    public static final String INSERT_MEDICATION = """
            INSERT INTO Medication (medicationName, medicationDescription, medicationQuantityInStock, medicationPrice, medicationExpiryDate, medicationManufacturer, medicationStatus, medicationCreatedAt, medicationUpdatedAt)
                    VALUES (:medicationName, :medicationDescription, :medicationQuantityInStock, :medicationPrice, :medicationExpiryDate, :medicationManufacturer, COALESCE(:medicationStatus,'ACTIVE'), NOW(), NOW())
            """;

    public static final String UPDATE_MEDICATION = "UPDATE Medication SET medicationName = :medicationName, medicationDescription = :medicationDescription, medicationQuantityInStock = :medicationQuantityInStock, medicationPrice = :medicationPrice, medicationExpiryDate = :medicationExpiryDate, medicationManufacturer = :medicationManufacturer, medicationUpdatedAt = NOW() WHERE medicationId=:medicationId";
    public static final String GET_ALL_MEDICATIONS = "SELECT * FROM Medication";
    public static final String GET_ALL_ACTIVE_MEDICATIONS = "SELECT * FROM Medication WHERE medicationStatus='ACTIVE'";
    public static final String GET_MEDICATION_BY_ID = "SELECT * FROM Medication WHERE medicationId=:medicationId";
    public static final String DELETE_MEDICATION = "UPDATE Medication SET medicationStatus = 'DELETED' WHERE medicationId=:medicationId";
}
