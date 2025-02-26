package com.qucoon.hospitalmanagement.mapper;

import com.qucoon.hospitalmanagement.model.entity.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Item.builder()
                .itemId(rs.getInt("itemId"))
                .itemMedicationId(rs.getInt("itemMedicationId"))
                .itemMedicationSalesId(rs.getInt("itemMedicationSalesId"))
                .itemQuantity(rs.getInt("itemQuantity"))
                .itemStatus(rs.getString("itemStatus"))
                .itemCreatedAt(rs.getString("itemCreatedAt"))
                .itemUpdatedAt(rs.getString("itemUpdatedAt"))
                .build();
    }
}
