package com.qucoon.hospitalmanagement.repository.query;

import com.qucoon.hospitalmanagement.model.entity.Equipment;
import java.util.HashMap;
import java.util.Map;

public class EquipmentQuery {

    public static final String INSERT_EQUIPMENT = """
    INSERT INTO Equipment (equipmentName, equipmentCategory, equipmentQuantity, equipmentPurchaseDate, equipmentStatus, equipmentCreatedAt, equipmentUpdatedAt)
    VALUES (:equipmentName, :equipmentCategory, :equipmentQuantity, :equipmentPurchaseDate, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
    """;

    public static final String UPDATE_EQUIPMENT = "UPDATE Equipment SET equipmentName = :equipmentName, equipmentCategory = :equipmentCategory, equipmentQuantity = :equipmentQuantity, equipmentPurchaseDate = :equipmentPurchaseDate, equipmentStatus = :equipmentStatus, equipmentUpdatedAt = CURRENT_TIMESTAMP WHERE equipmentId = :equipmentId";
    public static final String GET_ALL_EQUIPMENT = "SELECT * FROM Equipment where equipmentStatus != 'deleted'";

    public static final String GET_EQUIPMENT_BY_ID = "SELECT * FROM Equipment WHERE equipmentId = :equipmentId and equipmentStatus != 'deleted'";

    public static final String DELETE_EQUIPMENT = "Update Equipment set equipmentStatus = 'deleted' WHERE equipmentId = :equipmentId";

    /*public static String buildUpdateQuery(Equipment request, String equipment_id) {
        StringBuilder query = new StringBuilder("UPDATE Equipment SET ");
        Map<String, Object> params = new HashMap<>();

        if (request.getEquipmentName() != null) {
            query.append("equipmentName = :equipmentName, ");
            params.put("equipmentName", request.getEquipmentName());
        }
        if (request.getEquipmentCategory() != null) {
            query.append("equipmentCategory = :equipmentCategory, ");
            params.put("equipmentCategory", request.getEquipmentCategory());
        }
        if (request.getEquipmentQuantity() != null) {
            query.append("equipmentQuantity = :equipmentQuantity, ");
            params.put("equipmentQuantity", request.getEquipmentQuantity());
        }
        if (request.getEquipmentPurchaseDate() != null) {
            query.append("equipmentPurchaseDate = :equipmentPurchaseDate, ");
            params.put("equipmentPurchaseDate", request.getEquipmentPurchaseDate());
        }
        if (request.getEquipmentStatus() != null) {
            query.append("equipmentStatus = :equipmentStatus, ");
            params.put("equipmentStatus", request.getEquipmentStatus());
        }

        // Always update timestamp
        query.append("equipmentUpdatedAt = CURRENT_TIMESTAMP ");

        // Add WHERE condition
        query.append("WHERE equipmentId = :equipmentId");
        params.put("equipmentId", equipment_id);

        return query.toString();
    }*/


}
