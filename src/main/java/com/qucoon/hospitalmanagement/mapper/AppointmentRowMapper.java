package com.qucoon.hospitalmanagement.mapper;

import com.qucoon.hospitalmanagement.model.entity.Appointment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AppointmentRowMapper implements RowMapper<Appointment> {

    @Override
    public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Appointment.builder()
                .id(rs.getInt("id"))
                .patientId(rs.getInt("patient_id"))
                .doctorId(rs.getInt("doctor_id"))
                .appointmentDate(rs.getTimestamp("appointment_date"))
                .status(rs.getString("status"))
                .reason(rs.getString("reason"))
                .createdAt(rs.getTimestamp("created_at"))
                .updatedAt(rs.getTimestamp("updated_at"))
                .build();
    }
}
