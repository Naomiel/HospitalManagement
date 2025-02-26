package com.qucoon.hospitalmanagement.repository.implementation;

import com.qucoon.hospitalmanagement.mapper.AppointmentRowMapper;
import com.qucoon.hospitalmanagement.model.entity.Appointment;
import com.qucoon.hospitalmanagement.model.request.AppointmentCreateRequest;
import com.qucoon.hospitalmanagement.repository.Interface.AppointmentRepository;
import com.qucoon.hospitalmanagement.repository.query.AppointmentQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int createAppointment(Appointment appointment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientId", appointment.getPatientId())
                .addValue("doctorId", appointment.getDoctorId())
                .addValue("appointmentDate", appointment.getAppointmentDate())
                .addValue("status", appointment.getStatus())
                .addValue("reason", appointment.getReason())
                .addValue("createdAt", new Timestamp(System.currentTimeMillis()));
        return jdbcTemplate.update(AppointmentQuery.INSERT_APPOINTMENT, params);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return jdbcTemplate.query(AppointmentQuery.GET_ALL_APPOINTMENTS, new AppointmentRowMapper());
    }

    @Override
    public Appointment getAppointmentById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(AppointmentQuery.GET_APPOINTMENT_BY_ID, params, new AppointmentRowMapper());
    }

    @Override
    public int updateAppointment(int appointmentId, AppointmentCreateRequest appointment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId", appointmentId)
                .addValue("patientId", appointment.getPatientId())
                .addValue("doctorId", appointment.getDoctorId())
                .addValue("appointmentDate", appointment.getAppointmentDate())
                .addValue("reason", appointment.getReason())
                .addValue("updatedAt", new Timestamp(System.currentTimeMillis()));
        return jdbcTemplate.update(AppointmentQuery.UPDATE_APPOINTMENT, params);
    }

    @Override
    public int deleteAppointment(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(AppointmentQuery.DELETE_APPOINTMENT, params);
        return id;
    }

    @Override
    public int rescheduleAppointment(int appointmentId, Appointment appointment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId", appointmentId)
                .addValue("appointmentDate", appointment.getAppointmentDate())
                .addValue("updatedAt", new Timestamp(System.currentTimeMillis()));
        return jdbcTemplate.update(AppointmentQuery.RESCHEDULE_APPOINTMENT, params);
    }

    @Override
    public int cancelAppointment(int appointmentId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId", appointmentId)
                .addValue("updatedAt", new Timestamp(System.currentTimeMillis()));
        return jdbcTemplate.update(AppointmentQuery.CANCEL_APPOINTMENT, params);
    }
}

