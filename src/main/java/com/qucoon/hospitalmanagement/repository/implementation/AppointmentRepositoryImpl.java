package com.qucoon.hospitalmanagement.repository.implementation;

import com.qucoon.hospitalmanagement.mapper.GenericRowMapper;
import com.qucoon.hospitalmanagement.model.entity.Appointment;
import com.qucoon.hospitalmanagement.model.entity.ViewAppointment;
import com.qucoon.hospitalmanagement.model.request.AppointmentCreateRequest;
import com.qucoon.hospitalmanagement.repository.Interface.AppointmentRepository;
import com.qucoon.hospitalmanagement.repository.query.AppointmentQuery;
import com.qucoon.hospitalmanagement.repository.query.PatientQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int createAppointment(AppointmentCreateRequest appointment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentPatientId", appointment.getAppointmentPatientId())
                .addValue("appointmentStaffId", appointment.getAppointmentStaffId())
                .addValue("appointmentDate", appointment.getAppointmentDate())
                .addValue("appointmentStatus", appointment.getAppointmentStatus())
                .addValue("appointmentCreatedAt", new Timestamp(System.currentTimeMillis()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(AppointmentQuery.INSERT_APPOINTMENT, params, keyHolder, new String[]{"appointmentId"}); // Ensure "id" is the primary key column

        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
    }

    @Override
    public List<ViewAppointment> getAllAppointments() {
        return jdbcTemplate.query(AppointmentQuery.GET_ALL_APPOINTMENTS, new GenericRowMapper<>(ViewAppointment.class));
    }

    @Override
    public ViewAppointment getAppointmentById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("appointmentId", id);
        return jdbcTemplate.queryForObject(AppointmentQuery.GET_APPOINTMENT_BY_ID, params, new GenericRowMapper<>(ViewAppointment.class));
    }

    @Override
    public int updateAppointment(int appointmentId, AppointmentCreateRequest appointment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId", appointmentId)
                .addValue("appointmentPatientId", appointment.getAppointmentPatientId())
                .addValue("appointmentStaffId", appointment.getAppointmentStaffId())
                .addValue("appointmentDate", appointment.getAppointmentDate())
                .addValue("updatedAt", new Timestamp(System.currentTimeMillis()));
        return jdbcTemplate.update(AppointmentQuery.UPDATE_APPOINTMENT, params);
    }


    @Override
    public int deleteAppointment(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("appointmentId", id);
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

