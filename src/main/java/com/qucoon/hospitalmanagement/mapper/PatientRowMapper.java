package com.qucoon.hospitalmanagement.mapper;

import com.qucoon.hospitalmanagement.enums.Gender;
import com.qucoon.hospitalmanagement.model.entity.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRowMapper implements RowMapper<Patient> {
    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Patient.builder()
                .id(rs.getInt("patientId"))
                .firstName(rs.getString("patientFirstName"))
                .lastName(rs.getString("patientLastName"))
                .email(rs.getString("patientEmail"))
                .phoneNumber(rs.getString("patientPhoneNumber"))
                .gender(Gender.valueOf(rs.getString("patientGender")))
                .status(rs.getString("status"))
                .createdAt(rs.getString("createdAt"))
                .updatedAt(rs.getString("updatedAt"))
                .build();
    }
}
