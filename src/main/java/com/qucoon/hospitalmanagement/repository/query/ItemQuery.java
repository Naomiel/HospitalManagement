package com.qucoon.hospitalmanagement.repository.query;

public class ItemQuery {
    /*public static final String INSERT_ITEM = """
            INSERT INTO  Item (itemMedicationSalesId, itemMedicationId, itemQuantity, itemStatus, itemCreatedAt, itemUpdatedAt)
            VALUES (:itemMedicationSalesId, :itemMedicationId, :itemQuantity, COALESCE(:itemStatus,'ACTIVE'), NOW(), NOW())
            """;*/
    public static final String UPDATE_ITEM = "UPDATE Item SET itemMedicationSalesId = :itemMedicationSalesId, itemMedicationId = :itemMedicationId, itemQuantity = :itemQuantity, itemUpdatedAt = NOW() WHERE itemId = :itemId";
    public static final String GET_ALL_ITEMS = "SELECT * FROM Item";
    public static final String GET_ALL_ACTIVE_ITEMS = "SELECT * FROM Item WHERE itemStatus='ACTIVE'";
    public static final String GET_ITEM_BY_ID = "SELECT * FROM Item WHERE itemId = :itemId";
    public static final String DELETE_ITEM_BY_ID = "UPDATE Item SET itemStatus = 'DELETED' FROM Item WHERE itemId = :itemId";
}
