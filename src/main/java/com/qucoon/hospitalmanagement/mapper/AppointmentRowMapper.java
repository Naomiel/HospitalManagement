package com.qucoon.hospitalmanagement.mapper;

import com.qucoon.hospitalmanagement.model.entity.Appointment;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppointmentRowMapper implements RowMapper<Appointment> {

    @Override
    public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Appointment.builder()
                .appointmentId(rs.getInt("appointmentId"))
                .appointmentPatientId(rs.getInt("appointmentPatientId"))
                .appointmentStaffId(rs.getInt("appointmentStaffId"))
                .appointmentDate(rs.getTimestamp("appointmentDate"))
                .appointmentStatus(rs.getString("appointmentStatus"))
                .appointmentCreatedAt(rs.getTimestamp("appointmentCreatedAt"))
                .appointmentUpdatedAt(rs.getTimestamp("appointmentUpdatedAt"))
                .build();
    }
}
