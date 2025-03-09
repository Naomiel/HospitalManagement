package com.qucoon.hospitalmanagement.repository.implementation;


import com.qucoon.hospitalmanagement.mapper.PatientRowMapper;
import com.qucoon.hospitalmanagement.model.entity.Patient;
import com.qucoon.hospitalmanagement.model.request.PatientCreateRequest;
import com.qucoon.hospitalmanagement.repository.Interface.PatientRepository;
import com.qucoon.hospitalmanagement.repository.query.PatientQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Patient> getAllPatients() {
        return jdbcTemplate.query(PatientQuery.GET_ALL, new PatientRowMapper());
    }

    @Override
    public Patient getPatientById(int patientId) {
        MapSqlParameterSource params = new MapSqlParameterSource("patientId", patientId);
        List<Patient> patients = jdbcTemplate.query(PatientQuery.GET_BY_ID, params, new PatientRowMapper());
        return patients.isEmpty() ? null : patients.get(0);
    }

    @Override
    public int createPatient(PatientCreateRequest patient) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientFirstName", patient.getFirstName())
                .addValue("patientLastName", patient.getLastName())
                .addValue("patientEmail", patient.getEmail())
                .addValue("patientAge", patient.getAge())
                .addValue("patientPhoneNumber", patient.getPhoneNumber())
                .addValue("patientGender", patient.getGender().name())
                .addValue("patientAddress", patient.getAddress())
                .addValue("patientEmergencyContact", patient.getEmergencyContact())
                .addValue("patientStatus", patient.getStatus().name());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(PatientQuery.INSERT_PATIENT, params, keyHolder, new String[]{"patientId"}); // Ensure "id" is the primary key column

        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
    }

    @Override
    public int updatePatient(int patientId, PatientCreateRequest patient) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientId", patientId)
                .addValue("patientFirstName", patient.getFirstName())
                .addValue("patientLastName", patient.getLastName())
                .addValue("patientEmail", patient.getEmail())
                .addValue("patientAge", patient.getAge()) // ✅ Changed from `patientDob`
                .addValue("patientPhoneNumber", patient.getPhoneNumber())
                .addValue("patientGender", patient.getGender().name())
                .addValue("patientAddress", patient.getAddress())
                .addValue("patientEmergencyContact", patient.getEmergencyContact())
                .addValue("patientStatus", patient.getStatus() != null ? patient.getStatus() : "ACTIVE");
        return jdbcTemplate.update(PatientQuery.UPDATE_PATIENT, params);
    }



    @Override
    public int deletePatient(int patientId) {
        MapSqlParameterSource params = new MapSqlParameterSource("patientId", patientId);
        return jdbcTemplate.update(PatientQuery.DELETE_PATIENT, params);
    }
}
