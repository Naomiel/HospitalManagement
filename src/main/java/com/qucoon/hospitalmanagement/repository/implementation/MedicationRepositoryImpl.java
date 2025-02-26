package com.qucoon.hospitalmanagement.repository.implementation;
import com.qucoon.hospitalmanagement.mapper.MedicationMapper;
import com.qucoon.hospitalmanagement.model.entity.Medication;
import com.qucoon.hospitalmanagement.repository.Interface.MedicationRepository;
import com.qucoon.hospitalmanagement.repository.query.EquipmentQuery;
import com.qucoon.hospitalmanagement.repository.query.MedicationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicationRepositoryImpl implements MedicationRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public MedicationRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Medication> getAllMedications() {
        return jdbcTemplate.query(MedicationQuery.GET_ALL_MEDICATIONS, new MedicationMapper());
    }

    @Override
    public List<Medication> getAllActiveMedications() {
        return jdbcTemplate.query(MedicationQuery.GET_ALL_ACTIVE_MEDICATIONS, new MedicationMapper());
    }

    @Override
    public Medication getMedicationById(int medicationId) {
        MapSqlParameterSource params = new MapSqlParameterSource("medicationId", medicationId);
        return jdbcTemplate.queryForObject(MedicationQuery.GET_MEDICATION_BY_ID, params, new MedicationMapper());
    }

    @Override
    public int createMedication(Medication medication) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicationName", medication.getMedicationName())
                .addValue("medicationDescription", medication.getMedicationDescription())
                .addValue("medicationQuantityInStock", medication.getMedicationQuantityInStock())
                .addValue("medicationPrice", medication.getMedicationPrice())
                .addValue("medicationExpiryDate", medication.getMedicationExpiryDate())
                .addValue("medicationManufacturer", medication.getMedicationManufacturer())
                .addValue("medicationStatus", medication.getMedicationStatus());
        return jdbcTemplate.update(MedicationQuery.INSERT_MEDICATION, params);
    }

    @Override
    public int updateMedication(String medicationId, Medication medication) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicationId", medicationId)
                .addValue("medicationName", medication.getMedicationName())
                .addValue("medicationDescription", medication.getMedicationDescription())
                .addValue("medicationQuantityInStock", medication.getMedicationQuantityInStock())
                .addValue("medicationPrice", medication.getMedicationPrice())
                .addValue("medicationExpiryDate", medication.getMedicationExpiryDate())
                .addValue("medicationManufacturer", medication.getMedicationManufacturer());

        var sqlQuery = MedicationQuery.buildUpdateQuery(medication, String.valueOf(medicationId));
        return jdbcTemplate.update(sqlQuery, params);
    }

    @Override
    public int deleteMedication(int medicationid) {
        MapSqlParameterSource params = new MapSqlParameterSource("medicationId", medicationid);
        return jdbcTemplate.update(MedicationQuery.DELETE_MEDICATION, params);
    }
}
