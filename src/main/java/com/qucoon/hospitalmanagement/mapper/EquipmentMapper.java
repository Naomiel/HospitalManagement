package com.qucoon.hospitalmanagement.mapper;

import com.qucoon.hospitalmanagement.model.entity.Equipment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipmentMapper  implements RowMapper<Equipment> {

    @Override
    public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Equipment.builder()
                .equipmentId(rs.getInt("equipmentId"))
                .equipmentName(rs.getString("equipmentName"))
                .equipmentCategory(rs.getString("equipmentCategory"))
                .equipmentQuantity(rs.getInt("equipmentQuantity"))
                .equipmentPurchaseDate(rs.getString("equipmentPurchaseDate"))
                .equipmentStatus(rs.getString("equipmentStatus"))
                .equipmentCreatedAt(rs.getString("equipmentCreatedAt"))
                .equipmentUpdatedAt(rs.getString("equipmentUpdatedAt"))
                .build();


    }
}

