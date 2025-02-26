package com.qucoon.hospitalmanagement.mapper;

import com.qucoon.hospitalmanagement.model.entity.MedicationSales;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicationSalesMapper implements RowMapper<MedicationSales> {
    @Override
    public MedicationSales mapRow(ResultSet rs, int rowNum) throws SQLException {
        return MedicationSales.builder()
                .medicationSalesId(rs.getInt("medicationSalesId"))
                .medicationSalesPatientId(rs.getInt("medicationSalesPatientId"))
                .medicationSalesStaffId(rs.getInt("medicationSalesStaffId"))
                .medicationSalesCreatedAt(rs.getString("medicationSalesCreatedAt"))
                .medicationSalesUpdatedAt(rs.getString("medicationSalesUpdatedAt"))
                .build();
    }
}
