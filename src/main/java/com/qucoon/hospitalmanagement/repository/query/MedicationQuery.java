package com.qucoon.hospitalmanagement.repository.query;

import com.qucoon.hospitalmanagement.model.entity.Medication;
import com.qucoon.hospitalmanagement.model.entity.Medication;

import java.util.HashMap;
import java.util.Map;

public class MedicationQuery {
    public static final String INSERT_MEDICATION = """
            INSERT INTO Medication (medicationName, medicationDescription, medicationQuantityInStock, medicationPrice, medicationExpiryDate, medicationManufacturer, medicationStatus, medicationCreatedAt, medicationUpdatedAt)
                    VALUES (:medicationName, :medicationDescription, :medicationQuantityInStock, :medicationPrice, :medicationExpiryDate, :medicationManufacturer, COALESCE(:medicationStatus,'ACTIVE'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
            """;

    public static final String UPDATE_MEDICATION = "UPDATE Medication SET medicationName = :medicationName, medicationDescription = :medicationDescription, medicationQuantityInStock = :medicationQuantityInStock, medicationPrice = :medicationPrice, medicationExpiryDate = :medicationExpiryDate, medicationManufacturer = :medicationManufacturer, medicationUpdatedAt = CURRENT_TIMESTAMP WHERE medicationId=:medicationId";
    public static final String REDUCE_MEDICATION_QUANTITY= "UPDATE Medication SET medicationQuantityInStock=medicationQuantityInStock - :itemQuantity WHERE medicationId=:itemMedicationId";
    public static final String GET_ALL_MEDICATIONS = "SELECT * FROM Medication";
    public static final String GET_ALL_ACTIVE_MEDICATIONS = "SELECT * FROM Medication WHERE medicationStatus='ACTIVE'";
    public static final String GET_MEDICATION_BY_ID = "SELECT * FROM Medication WHERE medicationId=:medicationId AND medicationStatus='ACTIVE'";
    public static final String DELETE_MEDICATION = "UPDATE Medication SET medicationStatus = 'DELETED' WHERE medicationId=:medicationId";
    //public static final String GET_MEDICATIONS_QUANTITY_IN_STOCK = "SELECT medicationQuantityInStock FROM Medication WHERE medicationId=:medicationId AND medicationStatus='ACTIVE'";
    /*public static String buildUpdateQuery(Medication request, String medicationId) {
        StringBuilder query = new StringBuilder("UPDATE Medication SET ");
        Map<String, Object> params = new HashMap<>();

        if (request.getMedicationName() != null) {
            query.append("medicationName = :medicationName, ");
            params.put("medicationName", request.getMedicationName());
        }
        if (request.getMedicationDescription() != null) {
            query.append("medicationDescription = :medicationDescription, ");
            params.put("medicationDescription", request.getMedicationDescription());
        }
        if (request.getMedicationQuantityInStock() > 0) {
            query.append("medicationQuantityInStock = :medicationQuantityInStock, ");
            params.put("medicationQuantityInStock", request.getMedicationQuantityInStock());
        }
        if (request.getMedicationPrice() > 0) {
            query.append("medicationPrice = :medicationPrice, ");
            params.put("medicationPrice", request.getMedicationPrice());
        }
        if (request.getMedicationManufacturer() != null) {
            query.append("medicationManufacturer = :medicationManufacturer, ");
            params.put("medicationManufacturer", request.getMedicationManufacturer());
        }
        if (request.getMedicationExpiryDate() != null) {
            query.append("medicationExpiryDate = :medicationExpiryDate, ");
            params.put("medicationExpiryDate", request.getMedicationExpiryDate());
        }



        // Always update timestamp
        query.append("medicationUpdatedAt = CURRENT_TIMESTAMP ");

        // Add WHERE condition
        query.append("WHERE medicationId = :medicationId");
        params.put("medicationId", medicationId);

        return query.toString();
    }*/
}
