package com.qucoon.hospitalmanagement.mapper;

import com.qucoon.hospitalmanagement.model.request.MedicationRevenue;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MedicationRevenueMapper implements RowMapper<MedicationRevenue> {
    @Override
    public MedicationRevenue mapRow(ResultSet rs, int rowNum) throws SQLException {
        return MedicationRevenue.builder()
                .medicationId(rs.getInt("medicationId"))
                .startDate(rs.getString("startDate"))
                .endDate(rs.getString("endDate"))
                .build();
    }
}
