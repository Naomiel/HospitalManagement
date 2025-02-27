package com.qucoon.hospitalmanagement.repository.query;

import com.qucoon.hospitalmanagement.model.entity.Medication;
import com.qucoon.hospitalmanagement.model.entity.MedicationSales;

import java.util.HashMap;
import java.util.Map;

public class MedicationSalesQuery {
    public static final String INSERT_MEDICATION_SALES = """
            INSERT INTO MedicationSales (medicationSalesPatientId, medicationSalesStaffId, medicationSalesStatus, medicationSalesCreatedAt, medicationSalesUpdatedAt)
            VALUES (:medicationSalesPatientId, :medicationSalesStaffId, COALESCE(:medicationSalesStatus,'ACTIVE'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
            """;
    public static final String GET_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
    //public static final String UPDATE_MEDICATION_SALES = "UPDATE MedicationSales SET medicationSalesPatientId = :medicationSalesPatientId, medicationSalesStaffId = :medicationSalesStaffId, medicationSalesUpdatedAt = NOW() WHERE medicationSalesId=:medicationSalesId";
    public static final String GET_ALL_MEDICATION_SALES = "SELECT * FROM MedicationSales";
    public static final String GET_ALL_ACTIVE_MEDICATION_SALES = "SELECT * FROM MedicationSales WHERE medicationSalesStatus='ACTIVE'";
    public static final String GET_MEDICATION_SALES_BY_ID = "SELECT * FROM MedicationSales WHERE medicationSalesId=:medicationSalesId";
    public static final String DELETE_MEDICATION_SALES = "UPDATE MedicationSales SET medicationSalesStatus = 'DELETED' WHERE medicationSalesId=:medicationSalesId";
    public static final String INSERT_MEDICATION_SALES_ITEMS = "INSERT INTO Item (itemMedicationSalesId, itemMedicationId, itemQuantity) VALUES %s";
    public static final String GET_ALL_ACTIVE_MEDICATION_SALES_DETAILS = """
            SELECT
                ms.medicationSalesId,
                p.patientFirstName,
                p.patientLastName,
                p.patientAge,
                s.staffFirstName,
                s.staffLastName,
                s.staffRole
            FROM MedicationSales ms
            JOIN Patient p ON ms.medicationSalesPatientId = p.patientId
            JOIN Staff s ON ms.medicationSalesStaffId = s.staffId;""";

    public static final String GET_MEDICATION_SALES_DETAILS_BY_ID = """
            SELECT
                ms.medicationSalesId,
                p.patientFirstName,
                p.patientLastName,
                p.patientAge,
                s.staffFirstName,
                s.staffLastName,
                s.staffRole
            FROM MedicationSales ms
            JOIN Patient p ON ms.medicationSalesPatientId = p.patientId
            JOIN Staff s ON ms.medicationSalesStaffId = s.staffId
            WHERE ms.medicationSalesId=:medicationSalesId;""";

    public static String buildUpdateQuery(MedicationSales request, String medicationSalesId) {
        StringBuilder query = new StringBuilder("UPDATE MedicationSales SET ");
        Map<String, Object> params = new HashMap<>();

        if (request.getMedicationSalesPatientId() > 0) {
            query.append("medicationSalesPatientId = :medicationSalesPatientId, ");
            params.put("medicationSalesPatientId", request.getMedicationSalesPatientId());
        }
        if (request.getMedicationSalesStaffId() > 0) {
            query.append("medicationSalesStaffId = :medicationSalesStaffId, ");
            params.put("medicationSalesStaffId", request.getMedicationSalesStaffId());
        }


        // Always update timestamp
        query.append("medicationSalesUpdatedAt = CURRENT_TIMESTAMP ");

        // Add WHERE condition
        query.append("WHERE medicationSalesId = :medicationSalesId");
        params.put("medicationSalesId", medicationSalesId);

        return query.toString();
    }



}
