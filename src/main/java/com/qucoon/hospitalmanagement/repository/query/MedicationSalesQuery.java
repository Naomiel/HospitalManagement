package com.qucoon.hospitalmanagement.repository.query;

public class MedicationSalesQuery {
    public static final String INSERT_MEDICATION_SALES = """
            INSERT INTO MedicationSales (medicationSalesPatientId, medicationSalesStaffId, medicationSalesStatus, medicationSalesCreatedAt, medicationSalesUpdatedAt)
            VALUES (:medicationSalesPatientId, :medicationSalesStaffId, COALESCE(:medicationSalesStatus,'ACTIVE'), NOW(), NOW());
            """;
    public static final String GET_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
    public static final String UPDATE_MEDICATION_SALES = "UPDATE MedicationSales SET medicationSalesPatientId = :medicationSalesPatientId, medicationSalesStaffId = :medicationSalesStaffId, medicationSalesUpdatedAt = NOW() WHERE medicationSalesId=:medicationSalesId";
    public static final String GET_ALL_MEDICATION_SALES = "SELECT * FROM MedicationSales";
    public static final String GET_ALL_ACTIVE_MEDICATION_SALES = "SELECT * FROM MedicationSales WHERE medicationSalesStatus='ACTIVE'";
    public static final String GET_MEDICATION_SALES_BY_ID = "SELECT * FROM MedicationSales WHERE medicationSalesId=:medicationSalesId";
    public static final String DELETE_MEDICATION_SALES = "UPDATE MedicationSales SET medicationSalesStatus = 'DELETED' WHERE medicationSalesId=:medicationSalesId";
    public static final String INSERT_MEDICATION_SALES_ITEMS = "INSERT INTO Item (itemMedicationSalesId, itemMedicationId, itemQuantity) VALUES %s";
}
