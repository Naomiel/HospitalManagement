package com.qucoon.hospitalmanagement.repository.implementation;
import com.qucoon.hospitalmanagement.exception.DatabaseOperationException;
import com.qucoon.hospitalmanagement.mapper.MedicationMapper;
import com.qucoon.hospitalmanagement.model.entity.Medication;
import com.qucoon.hospitalmanagement.repository.Interface.MedicationRepository;
import com.qucoon.hospitalmanagement.repository.query.MedicationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@Repository
public class MedicationRepositoryImpl implements MedicationRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(MedicationRepositoryImpl.class);

    @Autowired
    public MedicationRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Medication> getAllMedications() {
        try {
            return jdbcTemplate.query(MedicationQuery.GET_ALL_MEDICATIONS, new MedicationMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            // Throw a custom exception
            throw new DatabaseOperationException("Failed to get all medications from the database", e);
        }
    }

    @Override
    public List<Medication> getAllActiveMedications() {
        try {
            return jdbcTemplate.query(MedicationQuery.GET_ALL_ACTIVE_MEDICATIONS, new MedicationMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            // Throw a custom exception
            throw new DatabaseOperationException("Failed to get all active medications from the database", e);
        }
    }

    @Override
    public Medication getMedicationById(int medicationId) {
        MapSqlParameterSource params = new MapSqlParameterSource("medicationId", medicationId);
        try {
            return jdbcTemplate.queryForObject(MedicationQuery.GET_MEDICATION_BY_ID, params, new MedicationMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            // Throw a custom exception
            throw new DatabaseOperationException("Failed to get medication from the database", e);
        }
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
        try {
            return jdbcTemplate.update(MedicationQuery.INSERT_MEDICATION, params);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);            // Throw a custom exception
            throw new DatabaseOperationException("Failed to insert medication into the database", e);
        }
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

        //var sqlQuery = MedicationQuery.buildUpdateQuery(medication, String.valueOf(medicationId));
        try {
            return jdbcTemplate.update(MedicationQuery.UPDATE_MEDICATION, params);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            // Throw a custom exception
            throw new DatabaseOperationException("Failed to update medication into the database", e);
        }
    }

    @Override
    public int deleteMedication(int medicationid) {
        MapSqlParameterSource params = new MapSqlParameterSource("medicationId", medicationid);
        try {
            return jdbcTemplate.update(MedicationQuery.DELETE_MEDICATION, params);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            // Throw a custom exception
            throw new DatabaseOperationException("Failed to delete medication", e);
        }
    }
}
