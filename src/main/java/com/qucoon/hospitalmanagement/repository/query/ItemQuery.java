package com.qucoon.hospitalmanagement.repository.query;

import com.qucoon.hospitalmanagement.model.entity.Item;
import com.qucoon.hospitalmanagement.model.entity.MedicationSales;

import java.util.HashMap;
import java.util.Map;

public class ItemQuery {
    public static final String INSERT_ITEM = """
            INSERT INTO  Item (itemMedicationSalesId, itemMedicationId, itemQuantity, itemStatus, itemCreatedAt, itemUpdatedAt)
            VALUES (:itemMedicationSalesId, :itemMedicationId, :itemQuantity, COALESCE(:itemStatus,'ACTIVE'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
            """;
    public static final String UPDATE_ITEM = "UPDATE Item SET itemMedicationSalesId = :itemMedicationSalesId, itemMedicationId = :itemMedicationId, itemQuantity = :itemQuantity, itemUpdatedAt = CURRENT_TIMESTAMP WHERE itemId = :itemId AND itemStatus = 'ACTIVE'";
    public static final String GET_ALL_ITEMS = "SELECT * FROM Item";
    public static final String GET_ALL_ACTIVE_ITEMS = "SELECT * FROM Item WHERE itemStatus='ACTIVE'";
    public static final String GET_ITEM_BY_MEDICATION_SALES_ID = """
                    SELECT
                     i.itemId,
                     m.medicationName,
                     m.medicationPrice,
                     i.itemQuantity
                        FROM Item i
                        JOIN Medication m ON i.itemMedicationId = m.medicationId
                     WHERE itemMedicationSalesId = :itemMedicationSalesId;""";
    public static final String GET_ITEM_BY_ID = "SELECT * FROM Item WHERE itemId = :itemId AND itemStatus='ACTIVE'";
    public static final String DELETE_ITEM_BY_MEDICATION_SALES_ID = "UPDATE Item SET itemStatus = 'DELETED' WHERE itemMedicationSalesId = :itemMedicationSalesId";
    public static final String DELETE_ITEM_BY_ID = "UPDATE Item SET itemStatus = 'DELETED' WHERE itemId = :itemId";
    /*public static String buildUpdateQuery(Item request, String itemId) {
        StringBuilder query = new StringBuilder("UPDATE Item SET ");
        Map<String, Object> params = new HashMap<>();

        if (request.getItemMedicationSalesId() > 0) {
            query.append("itemMedicationSalesId = :itemMedicationSalesId, ");
            params.put("itemMedicationSalesId", request.getItemMedicationSalesId());
        }
        if (request.getItemMedicationId() > 0) {
            query.append("itemMedicationId = :itemMedicationId, ");
            params.put("itemMedicationId", request.getItemMedicationId());
        }
        if (request.getItemQuantity() > 0) {
            query.append("itemQuantity = :itemQuantity, ");
            params.put("itemQuantity", request.getItemQuantity());
        }


        // Always update timestamp
        query.append("itemUpdatedAt = CURRENT_TIMESTAMP ");

        // Add WHERE condition
        query.append("WHERE itemId = :itemId");
        params.put("itemId", itemId);

        return query.toString();
    }*/
}
