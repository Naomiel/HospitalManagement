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
                .age(rs.getInt("patientAge"))
                .phoneNumber(rs.getString("patientPhoneNumber"))
                .gender(Gender.valueOf(rs.getString("patientGender")))
                .address(rs.getString("patientAddress"))
                .emergencyContact(rs.getString("patientEmergencyContact"))
                .status(rs.getString("patientStatus"))
                .createdAt(rs.getString("patientCreatedAt"))
                .updatedAt(rs.getString("patientUpdatedAt"))
                .build();
    }
}
