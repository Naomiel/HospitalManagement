package com.qucoon.hospitalmanagement.mapper;
import com.qucoon.hospitalmanagement.model.entity.Medication;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class MedicationMapper implements RowMapper<Medication> {
    @Override
    public Medication mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Medication.builder()
                .medicationId(rs.getInt("medicationId"))
                .medicationName(rs.getString("medicationName"))
                .medicationDescription(rs.getString("medicationDescription"))
                .medicationQuantityInStock(rs.getString("medicationQuantityInStock"))
                .medicationPrice(rs.getString("medicationPrice"))
                .medicationExpiryDate(rs.getString("medicationExpiryDate"))
                .medicationStatus(rs.getString("medicationStatus"))
                .medicationManufacturer(rs.getString("medicationManufacturer"))
                .medicationCreatedAt(rs.getString("medicationCreatedAt"))
                .medicationUpdatedAt(rs.getString("medicationUpdatedAt"))
                .build();
    }
}
