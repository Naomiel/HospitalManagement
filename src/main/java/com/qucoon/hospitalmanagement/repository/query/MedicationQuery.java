package com.qucoon.hospitalmanagement.repository.query;

public class MedicationQuery {
    public static final String INSERT_MEDICAION = """
            INSERT INTO Medication (medicationName, medicationDescription, medicationQuantityInStock, medicationPrice, medicationExpiryDate, medicationManufacturer, medication_status, medication_created_at, medication_updated_at)
                    VALUES (:medicationName, :medicationDescription, :medicationQuantityInStock, :medicationPrice, :medicationExpiryDate, :medicationManufacturer, COALESCE(:medictionStatus,'ACTIVE'), GETDATE(), GETDATE())
            """;

    public static final String UPDATE_MEDICATION = "UPDATE Medication SET medication_name = ?, medication_description = ?, medication_quantity_in_stock = ?, medication_price = ?, medication_expiry_date = ?, medication_manufacturer = ?, medication_updated_at = GETDATE() WHERE medicationId=:medicationId";
    public static final String GET_ALL_MEDICATIONS = "SELECT * FROM Medication";
    public static final String GET_ALL_ACTIVE_MEDICATIONS = "SELECT * FROM Medication WHERE medicationStatus='ACTIVE'";
    public static final String GET_MEDICATION_BY_ID = "SELECT * FROM Medication WHERE medicationId=:medicationId";
    public static final String DELETE_MEDICATION = "UPDATE Medication SET medicationStatus = 'DELETED' WHERE medicationId=:medicationId";
}
